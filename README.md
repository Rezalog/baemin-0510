
==============================================================================================================

1. Model
![1](https://github.com/Rezalog/baemin-0510/assets/70252834/567a95d9-69f0-4d73-8565-fdf6af09c995)

2. Saga

![cook](https://github.com/Rezalog/baemin-0510/assets/70252834/c6b8370a-3b08-409d-8bc0-a3614b08a549)
![delivery](https://github.com/Rezalog/baemin-0510/assets/70252834/6f145ea3-0afd-4c79-be3a-eb12984f421d)
![payment](https://github.com/Rezalog/baemin-0510/assets/70252834/d37c9324-87f2-4b08-ac3c-8b194a4f2d60)

3. CQRS
![1](https://github.com/Rezalog/baemin-0510/assets/70252834/20dcfac2-6807-478c-9e1b-7b2ef2dc6857)
![2](https://github.com/Rezalog/baemin-0510/assets/70252834/45e3688c-ae34-44d7-bf99-f8ac50e77829)
![3](https://github.com/Rezalog/baemin-0510/assets/70252834/9be7fb8c-e26b-49cd-a9b1-18936651a6d1)
![4](https://github.com/Rezalog/baemin-0510/assets/70252834/b486b503-0039-41fe-aa8e-7d8e45840812)
![5](https://github.com/Rezalog/baemin-0510/assets/70252834/1c469003-4865-494b-bf83-9514874cbe18)


4. compensation and correlation

![1](https://github.com/Rezalog/baemin-0510/assets/70252834/6742f6a1-b1d0-4f1e-9401-d468b26c4200)
![2](https://github.com/Rezalog/baemin-0510/assets/70252834/5b008b16-6d97-4bba-92e9-2e346b87a7ac)
![3](https://github.com/Rezalog/baemin-0510/assets/70252834/f0b38a92-6967-449c-b801-7b6d2c113c8d)



========================================================================================================================






# 

## Model
www.msaez.io/#/storming/baemin-msa

## Before Running Services
### Make sure there is a Kafka server running
```
cd kafka
docker-compose up
```
- Check the Kafka messages:
```
cd kafka
docker-compose exec -it kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic
```

## Run the backend micro-services
See the README.md files inside the each microservices directory:

- front
- store
- rider
- customer


## Run API Gateway (Spring Gateway)
```
cd gateway
mvn spring-boot:run
```

## Test by API
- front
```
 http :8088/orders id="id" foodId="foodId" customerId="customerId" options="options" address="address" 
 http :8088/payments id="id" orderId="orderId" status="status" foodId="foodId" options="options" address="address" 
```
- store
```
 http :8088/cooks id="id" status="status" foodId="foodId" storeId="storeId" customerId="customerId" options="options" orderId="orderId" 
```
- rider
```
 http :8088/deliveries id="id" address="address" orderId="orderId" status="status" storeId="storeId" customerId="customerId" 
```
- customer
```
```


## Run the frontend
```
cd frontend
npm i
npm run serve
```

## Test by UI
Open a browser to localhost:8088

## Required Utilities

- httpie (alternative for curl / POSTMAN) and network utils
```
sudo apt-get update
sudo apt-get install net-tools
sudo apt install iputils-ping
pip install httpie
```

- kubernetes utilities (kubectl)
```
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl
```

- aws cli (aws)
```
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install
```

- eksctl 
```
curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" | tar xz -C /tmp
sudo mv /tmp/eksctl /usr/local/bin
```

