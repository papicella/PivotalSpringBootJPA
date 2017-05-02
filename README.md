<h1>Pivotal Cloud Foundry Cloud Native Developer Workshop</h1>

![alt tag](https://ibb.co/cTWet5)

https://ibb.co/dUOQY5

Please join us at Pivotal for a half-day hands-on workshop to introduce concepts and development practices surrounding the use of Pivotal Cloud Foundry. This workshop provides attendees with the concepts and experience needed to work with and deploy applications on Pivotal Cloud Foundry. Attendees will gain experience in pushing apps to Pivotal Cloud Foundry, accessing logs, scaling and will explore topics directly related to the design and running of cloud-native applications, including metrics, high availability and blue-green deployments.


<h2>Pivotal Albums Page</h2>

![alt tag](https://dl.dropboxusercontent.com/u/15829935/platform-demos/workshop/level100/image1.png)

<h2> Steps to Run Locally (using H2 in memory database) </h2>

- Clone as follows

```
> git clone https://github.com/papicella/PivotalSpringBootJPA.git
```

- package as shown below

```
> mvn package
```

- Run as follows

```
mvn spring-boot:run
```

- Access as follows

```
http://localhost:8080/
```

<h2> Steps to Run On PCF (using MySQL instance if bound to such a service) </h2>

- package as shown below

```
> mvn package
```

- deploy using the default manifest.yml 

```
> cf push
```

manifest.yml is expecting a MySQL instance called "pas-mysql" if it does not exist change it to a name of one that exists

```
applications:
- name: pas-albums
  memory: 512M
  instances: 1
  random-route: true
  path: ./target/PivotalSpringBootJPA-0.0.1-SNAPSHOT.jar
  services:
    - pas-mysql
  env:
    JAVA_OPTS: -Djava.security.egd=file:///dev/urando
    SPRING_PROFILES_ACTIVE: cloud
```

<hr />
<i>
Pas Apicella [papicella at pivotal.io] is a Senior Platform Architect at Pivotal Australia
</i>

