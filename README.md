mongo rs init

```script
rs.initiate({
  _id: "rs0",
  members: [
    { _id: 0, host: "mongo1:27017" },
    { _id: 1, host: "mongo2:27017" },
    { _id: 2, host: "mongo3:27017" }
  ]
})
```

---
spring-boot app과 mongo을 같은 docker network에 두지 않으면, container name 기반으로 mongo 연결 불가  
mongo rs init에서 host을 localhost 로 세팅하면, mongo container간에 localhost로 인한 통신 불가  
다른 글(블로그)에서는 host파일을 127.0.0.1을 mongo1 과 같이 설정하는것으로 해결  

`org.mongodb.driver.cluster` 에서 mongo-replicas의 상태를 추적/관리 하여 primary 노드와 연결할 수 있게된다  

---
k8s 과 같은 환경이라면, ingress 설정을 통해 연결할 수 있으므로 local/dev/prod 등 환경에서도 모두 mongo-replicas 구조를 활용할 수 있을 것으로 보임
