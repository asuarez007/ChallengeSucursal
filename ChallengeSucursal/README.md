## Challenge RESTful  API with Spring Boot,Spring Security PostgreSQL, JPA, Docker,Lombok, Swagger mockito, Units 4,Healthcheck.

[Jose Francisco valdez]



## How it works:
## 0.1 Let's install PostgreSQL into your operating system.

As an open source object-relational database management system, PostgreSQL available for MacOS, Linux, and Windows.

## Goal for each Operating System
The goal will be to run the following command successfully from the command line (regardless of the OS):

```psql -U postgres```

This should open the psql interactive shell and print a prompt that looks like:

```postgres=# ```

## Installation per OS:

### MacOS:

Let's walk through installing PostgreSQL with the postgresapp on Mac.

1) Visit http://postgresapp.com/
2) Download the most recent version --> Click "Download"
3) Open the application, and click "initialize" to create a new PostgreSQL server
4) Ensure that the Postgres.app bin folder has been added to your $PATH;
  4.1) In the command line, enter: `echo "$PATH"`
  4.2) Search through the output and make sure Postgres.app/Contents/Version/latest/bin is there
       in order to ensure that this directory's executables are callable from any directory in bash.
5) In the command line, enter: `lsof -i tcp:5432`, and ensure that the postgres `COMMAND` appears.
   This checks if the Postgres server is now running on port 5432 under the name `localhost:postgresql`
   
### Linux:

1) Acquire the source code: `wget ftp://ftp.postgresql.org/pub/source/v9.3.2/postgresql-9.3.2.tar.bz2`
2) Install the packages needed for building Postgres:
   `sudo apt-get install build-essential libreadline-dev zlib1g-dev flex bison libxml2-dev libxslt-dev libssl-dev`

### Windows:
1) Download the installer specified by EnterpriseDB for all supported PostgreSQL versions. The installer is available here:
  https://www.postgresql.org/download/windows/


**0.2 Runs as Spring boot app**
* Clone the repository:
```bash
https://github.com/pilon33/jfv-challenge-subsidiary.git
```
* Update maven project:
```Runs As 
Spring Boot App
```
* Authorization:
```bash
username: `admin` and password: `admin` 

```
**1. Docker. First you need to install docker**
* Download Docker [Here](https://docs.docker.com/docker-for-windows/install/). Hint: Enable Hyper-V feature on windows and restart;
* Then open powershell and check:
```bash
docker info
```
or, and you see versions docker & docker compose
```bash
docker -v
```
```bash
docker-compose -v
```
**2. Spring boot app**
* Clone the repository:
```bash
https://github.com/pilon33/jfv-challenge-subsidiary.git
```
* Build the maven project:
```bash
mvn clean install
```
* Now run:
```bash
docker-compose up
```

Appendix A.

All commands should be run from project root (where docker-compose.yml locates)

**Guide for using endpoint the app:**
to test and would specify basic authorization a username: `admin` and password: `admin` 

* GET request to `/api/sucursales/` returns a list of "subsidiarys";
* GET request to `/api/sucursales/1` returns the "subsiadiary" with ID 1;
* POST request to `/api/sucursales/` with a "subsiadiary" object as JSON creates a new "subsiadiary";
* GET request to `/api/sucursalCercana/32.9697/-96.80322` origen latitude and longuitud returns the "subsiadiary by closest Distance" with Distance(KM) ;

or use Swagger API [http://localhost:8182/swagger-ui.html](http://localhost:8182/swagger-ui.html)

and generation API docks [http://localhost:8182/v2/api-docs.yaml](http://localhost:8182/v2/api-docs.yaml)

* Do not forget, if you see db, open the Windows Services Manager on your Windows 10 computer and stop postgres

**3. Docker control commands**
* Check all the images you have:
```bash
docker images
```
* If you have to want see running containers:
```bash
docker ps
```
**4. End stop app**
*  Stop containers:
```bash
docker-compose down
```
* Remove old stopped containers of docker-compose
```bash
docker-compose rm -f
```




