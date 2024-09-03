
### Prerequisite
- JDK 21
- Spring boot 3.3.3
- Gradle 8.8
- H2 DB
- JPA

### 프로젝트 구현 방향 
* repository 구현체 변경 시 의존성 최소화를 위해 헥사고날 아키텍처 구현
* API 의 정상 응답 구조와 에러 응답 구조 통일화를 위해 ApiResponseResource 구현
* Exception 처리의 중앙화를 위해 GlobalExceptionHandler 구현 
* csv, json 포멧의 문자열, file -> 컨버팅 로직 테스트 코드 작성
* prometheus 를 활용한 모니터링을 위한 로그 기능 구현
* OPEN API 제공을 위해 EmployeeApi 인터페이스에 메타 데이터 코드 작성, Controller 코드 분리는 위해 인터페이스를 활용. 

### 프로젝트 실행 방법
> * IDE Tool 에서 실행 active profile 를 local 로 설정
> * terminal 에서 실행 시 다음 명령어로 실행 -> ./gradlew bootRun --args='--spring.profiles.active=local' 


### OPEN API 접속 경로
> http://localhost:8080/swagger-ui/index.html

### prometheus 접속 경로
> http://localhost:9405/actuator/prometheus
