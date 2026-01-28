# Deployment Guide

This guide provides comprehensive instructions for deploying the Spring Boot REST API in various environments.

## 🚀 Deployment Options

### 1. Local Development

#### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL 12 or higher (optional, H2 available for development)
- Redis 6 or higher

#### Steps
```bash
# Clone the repository
git clone https://github.com/rskworld/spring-boot-api.git
cd spring-boot-api

# Build the application
mvn clean package

# Run the application
java -jar target/spring-boot-api-1.0.0.jar
```

#### Configuration
Update `src/main/resources/application.properties`:
```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/spring_boot_api
spring.datasource.username=postgres
spring.datasource.password=your_password

# Redis Configuration
spring.redis.host=localhost
spring.redis.port=6379
```

### 2. Docker Deployment

#### Using Docker Compose (Recommended)

```bash
# Clone the repository
git clone https://github.com/rskworld/spring-boot-api.git
cd spring-boot-api

# Start all services
docker-compose up -d

# View logs
docker-compose logs -f spring-boot-api
```

#### Services Included
- **Spring Boot API**: Port 8080
- **PostgreSQL**: Port 5432
- **Redis**: Port 6379
- **Nginx**: Ports 80, 443 (optional)

#### Docker Configuration Files

**docker-compose.yml:**
```yaml
version: '3.8'

services:
  postgres:
    image: postgres:15
    environment:
      POSTGRES_DB: spring_boot_api
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: password
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql
    networks:
      - spring-boot-network

  redis:
    image: redis:7-alpine
    ports:
      - "6379:6379"
    volumes:
      - redis_data:/data
    networks:
      - spring-boot-network

  spring-boot-api:
    build: .
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/spring_boot_api
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: password
      SPRING_REDIS_HOST: redis
      SPRING_REDIS_PORT: 6379
      SPRING_PROFILES_ACTIVE: docker
    ports:
      - "8080:8080"
    depends_on:
      - postgres
      - redis
    networks:
      - spring-boot-network

volumes:
  postgres_data:
  redis_data:

networks:
  spring-boot-network:
    driver: bridge
```

**Dockerfile:**
```dockerfile
FROM maven:3.9-openjdk-17 AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=builder /app/target/spring-boot-api-1.0.0.jar app.jar

RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 3. Production Deployment

#### System Requirements
- **CPU**: 2 cores minimum, 4 cores recommended
- **RAM**: 4GB minimum, 8GB recommended
- **Storage**: 20GB minimum, SSD recommended
- **OS**: Linux (Ubuntu 20.04+, CentOS 8+, RHEL 8+)

#### Step-by-Step Deployment

##### 1. Server Setup
```bash
# Update system
sudo apt update && sudo apt upgrade -y

# Install Java 17
sudo apt install openjdk-17-jdk -y

# Install Maven
sudo apt install maven -y

# Install PostgreSQL
sudo apt install postgresql postgresql-contrib -y

# Install Redis
sudo apt install redis-server -y

# Install Nginx (optional)
sudo apt install nginx -y
```

##### 2. Database Setup
```bash
# Create database
sudo -u postgres createdb spring_boot_api

# Create user
sudo -u postgres psql -c "CREATE USER api_user WITH PASSWORD 'secure_password';"

# Grant privileges
sudo -u postgres psql -c "GRANT ALL PRIVILEGES ON DATABASE spring_boot_api TO api_user;"

# Run initialization script
sudo -u postgres psql -d spring_boot_api -f init.sql
```

##### 3. Application Deployment
```bash
# Clone repository
git clone https://github.com/rskworld/spring-boot-api.git
cd spring-boot-api

# Create production properties
cp src/main/resources/application.properties src/main/resources/application-prod.properties

# Edit production configuration
nano src/main/resources/application-prod.properties
```

**Production Configuration:**
```properties
# Server Configuration
server.port=8080
server.servlet.context-path=

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/spring_boot_api
spring.datasource.username=api_user
spring.datasource.password=secure_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Redis Configuration
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.password=your_redis_password

# JWT Configuration
jwt.secret=your-super-secure-secret-key-here
jwt.expiration=86400000
jwt.refresh-token.expiration=604800000

# Logging Configuration
logging.level.com.rskworld=INFO
logging.level.org.springframework.security=WARN
logging.file.name=/var/log/spring-boot-api/application.log
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
```

##### 4. Build and Run
```bash
# Build application
mvn clean package -DskipTests

# Create service user
sudo useradd -r -s /bin/false springboot

# Create directories
sudo mkdir -p /var/lib/spring-boot-api
sudo mkdir -p /var/log/spring-boot-api

# Copy JAR file
sudo cp target/spring-boot-api-1.0.0.jar /var/lib/spring-boot-api/

# Set permissions
sudo chown -R springboot:springboot /var/lib/spring-boot-api
sudo chown -R springboot:springboot /var/log/spring-boot-api
```

##### 5. Systemd Service
```bash
# Create service file
sudo nano /etc/systemd/system/spring-boot-api.service
```

**Service Configuration:**
```ini
[Unit]
Description=Spring Boot API
After=syslog.target network.target

[Service]
User=springboot
Group=springboot
ExecStart=/usr/bin/java -jar /var/lib/spring-boot-api/spring-boot-api-1.0.0.jar
SuccessExitStatus=143
Restart=on-failure
RestartSec=10
StandardOutput=journal
StandardError=journal
SyslogIdentifier=spring-boot-api

Environment=SPRING_PROFILES_ACTIVE=prod
Environment=JAVA_OPTS=-Xmx2g -Xms1g

[Install]
WantedBy=multi-user.target
```

```bash
# Enable and start service
sudo systemctl daemon-reload
sudo systemctl enable spring-boot-api
sudo systemctl start spring-boot-api

# Check status
sudo systemctl status spring-boot-api
```

### 4. Cloud Deployment

#### AWS EC2

1. **Launch EC2 Instance**
   - Choose Ubuntu 20.04 LTS
   - Select t3.medium or larger
   - Configure security groups (ports 80, 443, 22)

2. **Deploy Application**
   ```bash
   # SSH into instance
   ssh -i your-key.pem ubuntu@your-ec2-ip
   
   # Follow production deployment steps
   ```

3. **Configure Security Groups**
   - HTTP (80): 0.0.0.0/0
   - HTTPS (443): 0.0.0.0/0
   - SSH (22): Your IP

#### Google Cloud Platform

1. **Create Compute Engine Instance**
   ```bash
   gcloud compute instances create spring-boot-api \
     --machine-type=e2-medium \
     --image-family=ubuntu-2004-lts \
     --image-project=ubuntu-os-cloud \
     --zone=us-central1-a
   ```

2. **Deploy Application**
   ```bash
   gcloud compute ssh spring-boot-api --zone=us-central1-a
   # Follow production deployment steps
   ```

#### Azure Virtual Machine

1. **Create VM**
   - Ubuntu Server 20.04 LTS
   - Standard_B2s or larger
   - Configure network security group

2. **Deploy Application**
   ```bash
   ssh user@your-vm-ip
   # Follow production deployment steps
   ```

### 5. Kubernetes Deployment

#### Kubernetes Manifests

**deployment.yaml:**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-boot-api
  labels:
    app: spring-boot-api
spec:
  replicas: 3
  selector:
    matchLabels:
      app: spring-boot-api
  template:
    metadata:
      labels:
        app: spring-boot-api
    spec:
      containers:
      - name: spring-boot-api
        image: rskworld/spring-boot-api:1.0.0
        ports:
        - containerPort: 8080
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: "kubernetes"
        - name: SPRING_DATASOURCE_URL
          valueFrom:
            secretKeyRef:
              name: db-secret
              key: url
        - name: SPRING_DATASOURCE_USERNAME
          valueFrom:
            secretKeyRef:
              name: db-secret
              key: username
        - name: SPRING_DATASOURCE_PASSWORD
          valueFrom:
            secretKeyRef:
              name: db-secret
              key: password
        resources:
          requests:
            memory: "512Mi"
            cpu: "500m"
          limits:
            memory: "1Gi"
            cpu: "1000m"
        livenessProbe:
          httpGet:
            path: /health
            port: 8080
          initialDelaySeconds: 60
          periodSeconds: 30
        readinessProbe:
          httpGet:
            path: /health
            port: 8080
          initialDelaySeconds: 30
          periodSeconds: 10
```

**service.yaml:**
```yaml
apiVersion: v1
kind: Service
metadata:
  name: spring-boot-api-service
spec:
  selector:
    app: spring-boot-api
  ports:
  - protocol: TCP
    port: 80
    targetPort: 8080
  type: LoadBalancer
```

#### Deploy to Kubernetes
```bash
# Apply manifests
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml

# Check deployment
kubectl get pods
kubectl get services
```

## 🔧 Configuration Management

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_PROFILES_ACTIVE` | Active profile | `dev` |
| `SERVER_PORT` | Server port | `8080` |
| `SPRING_DATASOURCE_URL` | Database URL | `jdbc:h2:mem:testdb` |
| `SPRING_DATASOURCE_USERNAME` | DB username | `sa` |
| `SPRING_DATASOURCE_PASSWORD` | DB password | `` |
| `SPRING_REDIS_HOST` | Redis host | `localhost` |
| `SPRING_REDIS_PORT` | Redis port | `6379` |
| `JWT_SECRET` | JWT secret key | `mySecretKey...` |

### Profile-Specific Configurations

- **dev**: Development with H2 database
- **docker**: Docker environment with PostgreSQL
- **prod**: Production with PostgreSQL and Redis
- **kubernetes**: Kubernetes environment

## 📊 Monitoring and Logging

### Application Monitoring

#### Health Checks
```bash
# Application health
curl http://localhost:8080/health

# Metrics (if enabled)
curl http://localhost:8080/actuator/metrics
```

#### Log Management
```bash
# View application logs
tail -f /var/log/spring-boot-api/application.log

# Systemd logs
journalctl -u spring-boot-api -f
```

### Performance Monitoring

#### JVM Monitoring
```bash
# Install monitoring tools
sudo apt install htop iotop

# Monitor Java process
jstat -gc <pid>
jstack <pid>
```

#### Database Monitoring
```bash
# PostgreSQL connections
sudo -u postgres psql -c "SELECT * FROM pg_stat_activity;"

# Redis monitoring
redis-cli info
```

## 🔒 Security Considerations

### Production Security Checklist

- [ ] Change default passwords
- [ ] Use HTTPS in production
- [ ] Configure firewall rules
- [ ] Enable security headers
- [ ] Regular security updates
- [ ] Backup strategy
- [ ] Access control
- [ ] Audit logging

### SSL/TLS Configuration

#### Using Let's Encrypt
```bash
# Install Certbot
sudo apt install certbot python3-certbot-nginx

# Obtain certificate
sudo certbot --nginx -d yourdomain.com

# Auto-renewal
sudo crontab -e
# Add: 0 12 * * * /usr/bin/certbot renew --quiet
```

## 🚨 Troubleshooting

### Common Issues

#### Application Won't Start
```bash
# Check logs
journalctl -u spring-boot-api -n 50

# Check Java version
java -version

# Check port availability
netstat -tulpn | grep 8080
```

#### Database Connection Issues
```bash
# Test PostgreSQL connection
psql -h localhost -U api_user -d spring_boot_api

# Check PostgreSQL status
sudo systemctl status postgresql
```

#### Redis Connection Issues
```bash
# Test Redis connection
redis-cli ping

# Check Redis status
sudo systemctl status redis
```

### Performance Issues

#### Memory Usage
```bash
# Check memory usage
free -h
top -p <java-pid>

# Increase heap size
export JAVA_OPTS="-Xmx4g -Xms2g"
```

#### Database Performance
```bash
# Check slow queries
sudo -u postgres psql -d spring_boot_api -c "SELECT * FROM pg_stat_statements;"

# Optimize database
sudo -u postgres psql -d spring_boot_api -c "VACUUM ANALYZE;"
```

## 📞 Support

For deployment support:
- **Email**: help@rskworld.in, support@rskworld.in
- **Website**: https://rskworld.in
- **Phone**: +91 93305 39277

---

© 2026 RSK World. All rights reserved.
