# 내 위치 기반 공공 와이파이 정보를 제공하는 WIFIYEAH
- 개발기간 : 2022.2.24 ~ 2022.3.1
- 제로베이스 개인 과제
- 2022.3.1 업데이트

<img src="/img/thumbnail.png">

<br><br>

## 📌 개발 환경
|     분야      |         stack          |  
|:-----------:|:----------------------:|  
|     언어      |    open jdk 11.0.16    |  
|     DB      | MySQL-Connector 5.1.49 |  
|     빌드툴     |         Gradle         |  
| Persistence |          JDBC          |  
|     IDE     |        IntelliJ        |
|     서버      |     Tomcat 9.0.72      |
|   템플릿 엔진    |          JSP           |
|   서버 플랫폼    |        JavaEE8         |


<br><br>

## 📌 ERD
<img src="/img/erd.png" width="70%" height="70%">

<br><br>

## 📌 명세
### ◎ Open API 와이파이 정보 가져오기
- 요청
`http://openapi.seoul.go.kr:8088/sample/json/TbPublicWifiInfo/1/5`
- 응답
```json
[
  {
    "TbPublicWifiInfo": {
      "list_total_count": 22361,
      "RESULT": {
        "CODE": "INFO-000",
        "MESSAGE": "정상 처리되었습니다"
      },
      ", ": [
        {
          "X_SWIFI_MGR_NO": "서울-6047-2",
          "X_SWIFI_WRDOFC": "강남구",
          "X_SWIFI_MAIN_NM": "강남장애인복지관",
          "X_SWIFI_ADRES1": "서울시 강남구 개포로 605",
          "X_SWIFI_ADRES2": "강남장애인복지관(옥내6) 지하2층",
          "X_SWIFI_INSTL_FLOOR": "",
          "X_SWIFI_INSTL_TY": "6-3. 복지 - 장애인",
          "X_SWIFI_INSTL_MBY": "디지털뉴딜(LG U+)",
          "X_SWIFI_SVC_SE": "과기부WiFi(복지시설)",
          "X_SWIFI_CMCWR": "인터넷망_뉴딜용",
          "X_SWIFI_CNSTC_YEAR": "2022",
          "X_SWIFI_INOUT_DOOR": "실내",
          "X_SWIFI_REMARS3": "",
          "LAT": "127.07347",
          "LNT": "37.491985",
          "WORK_DTTM": "2023-02-28 10:58:27.0"
        }
      ]
    }
  }
]
```

<br>

### ◎ 근처 와이파이 정보 보기
1. 내 위치 가져오기
- JavaScript `navigator.geolocation` 사용

2. 내 위치 저장
- 요청

  |이름|타입|설명|
  |------|---|---|
  |x|float|사용자의 위도|
  |y|float|사용자의 경도|
  |create_tme|Timestamp|저장 시간|

3. 와이파이와 나 사이의 거리 저장
- 요청

  |이름|타입|설명|
  |------|---|---|
  |lat|float|사용자의 위도|
  |lnt|float|사용자의 경도|

4. 근처 와이파이 조회
- 응답

  | 이름                | 타입        | 설명                  |
  |--------------------|---------------------|-------------|
  | ditance            | float     | 사용자와 공유 와이파이 사이의 거리 |
  | manage_num         | String    | 공유 와이파이 식별자         |
  | region             | String    | 자치구                 |
  | wifi_name          | String    | 와이파이 이름             |
  | road_address       | String    | 도로명주소               |
  | detailed_address   | String    | 상세주소                |
  | floor              | String    | 층                   |
  | installation_type  | String    | 설치 유형               |
  | organization       | String    | 기관                  |
  | classified_service | String    | 서비스 분류              |
  | network_type       | String    | 망 타입                |
  | year_of_install    | int       | 설치 년도               |
  | in_or_out          | String    | 실내/실외               |
  | conn_environment   | String    | 설치 환경               |
  | lat                | float     | 위도                  |
  | lnt                | float     | 경도                  |
  | worktime           | Timestamp | 마이그레이션 시간           |

<br>

### ◎ 위치 히스토리 목록
- 응답

  | 이름  |타입|설명|
  |-----|------|---|
  | id   |Integer|위치 히스토리 식별자|
  | x |float|사용자의 위도|
  | y |float|사용자의 경도|
  | created_time |Timestamp|생성시간|

<br><br>

## 📌 트러블 슈트
<details>
<summary>⭐ 마이그레이션 반복 시 와이파이 테이블 초기화 🆚 업데이트</summary>
<div markdown="1">

- 마이그레이션을 반복 할 때 PK 중복 예외가 발생 했다. 멘토님께 과제 설명을 들을때 중복 되는 PK만 선별 하거나 특정 시간이 지난 데이터는 삭제하는 등의? 조언을 주셨던 것 같은데,
잘 기억이 나지 않았다. 나는 그냥 마이그레이션 전에 모든 데이터를 삭제하는 간단한 방식을 선택했는데, 더 고도화된 다른 방식은 무엇이 있고, 성능과 관련하여 방식마다의 포인트에 대해 멘토님께 여쭙고 싶다.
</div>
</details>

<details>
<summary>jdbc template</summary>
<div markdown="1">

- 역시 시간이 남으면 해봐야겠다.. 싶었는데 JPA나 데이터베이스 개념 자체에 더 관심이 많아서 소홀했던 것 같다. 리팩토링 시간에 한번 찾아보고 수정까지는 안하더라도 정리는 해놓을 예정이다.
</div>
</details>

<details>
<summary>jdbc transaction</summary>
<div markdown="1">

- 트랜잭션이 없으면 안 된다는 것을 안다. 네이티브 sql로 `conn.commit()`, `conn.rollback()` 같은 코드를 사용한다는 것만 알고 시간이 남으면 해봐야지 했는데, 
추가하지 못했다. sql이나 데이터베이스가 요즘 부쩍 매우 중요하다고 느끼고 있어서 꼭 정리할 예정이다.
</div>
</details>

<details>
<summary>⭐️ 톰캣9, javaEE8, my-sql Connector5 버전 호환</summary>
<div markdown="1">

- 초반에 과제를 하면서 jsp 없이 자바로만 jdbc crud, open api 구현을 시작했는데, jsp를 거치니 자바로는 동작하던 코드들이 동작하지 않았다.
- 왜 자바로는 mysql 드라이버를 읽으면서 jsp를 거칠때만 읽지 못하는지 의문이었다. 문제가 생기면 마구잡이로 (어쩌면 너무 여유가 없어서..?) 원인을 찾아해멨는데, 
이 경험으로 하나씩 기록하며 체크해야할 요소들을 하나씩 지워나가는 방식을 얻은 것 같다.
- Jakarta 에서 JavaEE8로 바꿔 프로젝트를 다시 생성하고, 톰캣을 8에서 9로 바꾸고, mysql-connector를 8에서 5로 낮추니 동작했다. 스프링 부트만 사용해봐서 의존도 간 버전 호환성을 맞춰야 한다는 생각 자체를 못한 것 같다.
고대의 개발자님들은 라이브러리간의 호환성을 다 하나하나 맞추었겠구나 생각을 하니....이 시대에 태어난 것이 정말 다행이었다.
</div>
</details>

<details>
<summary>can not find symbol</summary>
<div markdown="1">

- 별건 아니지만 3번은 만난 것 같다. 캐시를 비우거나 리빌드 하는 등의 방법들이 있다.
- 나는 주로 gradle의 build and run using: 에서 gradle -> IndelliJ로 바꾸면서 해결이 됐는데 브런치 마다 다른 환경이 설정된다는 것을 이번에 알았다.
</div>
</details>

<details>
<summary>PK 중복️ (distance)</summary>
<div markdown="1">

- distance가 저장될때 PK 중복이 날때도 안날때도 있었다. 파고들까 하다가 리팩토링 시간에 테이블 개수를 줄이고, DTO에서 내부클래스를 사용해보고 싶어서 일단 미루었다.
</div>
</details>

<br><br>

## 📌 아쉬운점

<details>
<summary>⭐ 마이그레이션 시간 단축</summary>
<div markdown="1">

- 리팩토링 시간을 꼭 가지려는 첫번째 이유다.
- open api로 22000여개의 데이터를 가져오는데 무려 약 5분이 걸린다. 솔직히 구현에만 급급하지 아직 성능을 따지지 못하는데, 리팩토링 하면서 가장 많은 것을 배울 수 있을 것 같다.

- <img src="/img/수행시간.png" width="70%" height="70%">

</div>
</details>

<details>
<summary>예외처리</summary>
<div markdown="1">

- api 형식의 프로젝트에서는 에러코드와 에러메시지를 프론트로 보내주면 프론트에서 데이터를 사용해서 화면에 alert 하는 식으로 에러를 처리했었다. 
- 템플릿 엔진을 다뤄보면서 에러 페이지를 따로 만들어야 한다고 알고만 있었는데, 자바코드에서 에러가 나면 페이지 이동없이 js로 alert 하는 에러처리를 해보고 싶었다. 
- 나중에 찾아봐야지 미루다가 마감전에 하지 못했다. api 형식에 더 관심이 많아서 안 찾아보게 되는 것도 있지만, 해당 프로젝트를 리팩토링 하는 시간에 찾아볼 예정이다. 
</div>
</details>

<details>
<summary> 테스트</summary>
<div markdown="1">

- jsp에서 데이터가 잘 들어오는지 확인할 부분이 많기는 했지만 너무 sout을 남발한 것 같다. 
테스트 클래스를 생성하고 junit으로 테스트 하거나 인텔리제이로 디버깅하는 방식을 배우고 접해보기는 했는데, 정리를 아직 못했고 또 해본 적도 없어서 사실 익숙하지 않다.
다음 과제 전까지 배운 테스트 방식을 잘 정리해서, TDD 까지는 아니더라도 테스트 클래스를 반드시 생성하여 효율적으로 테스트하는 것이 목표다.
</div>
</details>

<details>
<summary>⭐️ 서비스 객체를 나누어 메서드간 결합 끊기</summary>
<div markdown="1">

- 리팩토링 대상 최우선순위 중 하나다.
- 역시 나중에 계층 나눠야지 하고 일단 Service에 로직을 다 넣었는데, 계층을 나누는 것은 고사하고 `내 주변 와이파이 조회` 동작의 `getWifi20()` 메서드 내부에서
호출하는 메서드들이 다 다른 도메인을 다룬다. 도메인별 서비스 클래스도 분리하고, 메서드 내부에서 메서드를 호출하지 않고 호출부 자체에서 별개의 도메인을 다루는 메서드를 각각 호출하는 방식으로 메서드 간의 결합도를 낮추고 싶다.
</div>
</details>

<details>
<summary>히스토리 삭제 jstl</summary>
<div markdown="1">

- jstl이 익숙하지 않아서 굳이 사용을 안하다가, 삭제를 구현할때 찾아보다가 jdbc delete + jsp 조합으로 사람들이 jstl을 많이 사용한다는 것을 알게 됐다.
- 리팩토링 시간을 가지면서 추가할 예정이다.
</div>
</details>

<details>
<summary>북마크</summary>
<div markdown="1">

- 나는 제로베이스에서 챌린지 반인데, 마스터반은 북마크 기능도 구현해야 한다. 
- 외래키나 join에 빠져있는 요즘이라 네이티브 sql로 구현한 답안을 꼭 보고 싶기도 하고, 혼자서도 기능을 추가해보고 싶다.
</div>
</details>

<details>
<summary>프론트</summary>
<div markdown="1">

- html, js, css에 부족함을 느꼈다. 사실 그렇게 큰 욕심이 없는 부분인데, 취직 하면 프론트도 한다 라던가 프론트를 이해하는 것이 백엔드에도 도움이 된다 라는 등의 
조언을 많이 받아서 경각심을 가지게 됐다. 이번 과제를 진행하면서 개념을 접할때마다 정리해야겠다고 마음먹었다.
</div>
</details>



<br><br>

## 📌 배운점
<details>
<summary>Servlet/JSP</summary>
<div markdown="1">

- Servlet/JSP 라고 늘 세트처럼 들어서 과제 전에 당연히 Servlet을 사용해야겠다고 생각했었는데, Servlet/JSP 코드를 많이 찾아보면서 정적 화면간 url 매핑을 굳이 
Servlet으로 안하고 JSP 에서만 연결하며, 자바 코드는 import 태그로 사용 가능하다는 것을 알게 됐다. 
</div>
</details>

<details>
<summary>초기화 방식 - 빌더패턴</summary>
<div markdown="1">

- 초기화 방식에서 생성자, 세터 방식과 다르게 빌더패턴이 갖는 이점은 이러하다.
  - 필드명을 메서드처럼 사용하므로 가독성에 좋다.
  - 필요한 데이터만 설정이 가능하며 필드 선언 순서에 독립적이다. (버그방지)
  - 한번에 생성해야 하므로 변경 가능성이 없다. (불변성 지향)
- 빌더패턴을 접한 이후 처음으로 DTO를 세팅하는데에 적극적으로 사용해봤다. 확실히 편하고 깔끔하다고 느꼈다.
</div>
</details>

<details>
<summary>⭐ 외래키 없이 조인</summary>
<div markdown="1">

- 사실 조인을 이해한지 얼마 안되서 `wifi` 테이블과 `distance` 테이블에 join query를 사용하는 것이 가장 재미있었다.
- JPA를 공부하면서 많이 어려웠는데, native sql을 공부하면서 해소되는 부분이 많았다. 아직 공부하고 있는 부분이지만, join을 직접 사용해볼 수 있어서 좋았다.
  (~~FK도 사용해봐야지~~)
</div>
</details>

<details>
<summary>⭐ 직렬화/역직렬화</summary>
<div markdown="1">

- 스프링 부트 프로젝트를 해보면서 `@RequestBody`, `@ResponseBody` 만 사용해봤다. 
json 데이터를 받아서 직접 객체로 변환하고, 역시 `@GetMapping()` 없이 `HttpUrlConnect`라이브러리로 http 통신을 했다.
</div>
</details>

<details>
<summary>공부방식</summary>
<div markdown="1">

- 그냥 알고 이해한뒤 흘러보내거나 정리에 시간을 너무 많이 들이는 등 공부방식에 만족하지 못하고 있었는데, 
과제 진행 시기와 맞물려 정확하고 간결하게 정리한 다음에 다시 찾아가서 볼때 들이는 시간을 줄이는 형태의 공부 방식이 생겼고 이 방식에 스스로 만족하고 있다.
</div>
</details>

<br><br>

## 📌 총평
dto 내부 클래스 라던가, 테스트 방식 등 새로운 방식 보다는 일단 어떻게라도 구현을 한 뒤 나중에 리팩토링 해봐야지 라는 생각을 가지고 있었는데,
늦장을 부리다 시간이 없기도 했고 나중에 바꾸려니 더 머리가 아팠다.
다음 과제에서는 삽질로 시간이 많이 걸릴까 아무리 두려워도 새로운 시도를 다 해보고 싶다.
