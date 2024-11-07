# 일정관리 앱

API 명세서
--------------

| 기능 | Method | **URL** | **request** | **response** | **상태코드** |
| --- | --- | --- | --- | --- | --- |
| 일정 등록 | POST | /api/schedules  | 요청 body | 등록 정보 | 200 OK, 400 비정상 값 |
| 전체 일정 조회 | GET | /api/schedules |  | 다건 응답 정보 | 200 OK |
| 선택 일정 조회 | GET | /api/schedules/{schedules_id} | 요청 param(id) | 단건 응답 정보 | 200 OK, 404 선택한 일정이 사라짐 |
| 일정 수정 | PUT | /api/schedules/{schedules_id}  | 요청 param(id), 요청 body | 수정 정보 | 200 OK, 400 비정상 값, 403 비밀번호 틀림 |
| 일정 삭제 | DELETE | /api/schedules/{schedules_id}  | 요청 param(id) | 삭제 정보 | 200 OK, 403 비밀번호 틀림, 404 선택한 일정이 사라짐 |

1. 일정등록
  - 요청(request) : POST /api/schedules
  ```
{
	"userName":"고강혁",
	"password" : "123123"
	"title" : "일정1",
	"content" : "공부"
}
  ```
  - 응답(response)
```
{
  "schedule_id" : "1"
}
```
2. 전체 일정 조회
- 요청(request) : X
- 응답(response)
```
{
  "schedules" :[ {
    "schedule_id": "1",
    "userName" : "고강혁"
    "title" : "일정1",
    "content" : "공부",
    "createDate":"2024-11-01 12:22:10",
    "updateDate":"2024-11-01 12:22:10"
}, {
        "schedule_id": "2",
        "userName" : "박영배"
        "title" : "일정2",
        "content" : "상담",
        "createDate":"2024-11-01 12:22:10",
        "updateDate":"2024-11-01 12:22:10"
    },
]
}
```
3. 선택 일정 조회
- 요청(request) : GET /api/schedules/{schedule_id}
- 응답(response)
```
{
    "schedule_id": "1",
    "userName" : "고강혁"
    "title" : "일정1",
    "content" : "공부",
    "createDate":"2024-11-01 12:22:10",
    "updateDate":"2024-11-01 12:22:10"
}
```
4. 일정 수정
- 요청(request) : PUT /api/schedules/{schedule_id}
```
{
    "userName" : "고강혁"
    "password" : "123123"
    "title" : "일정1",
    "content" : "공부",
}
```
- 응답(response)
```
{
  "schedule_id" : "1"
}
```
5. 일정 삭제
- 요청(request) : DELETE /api/schedules/{schedule_id}
```
{
    "password" : "123123"
}
```
- 응답(response)
```
{
  "schedule_id" : "1"
}
```
*** 

ERD
----------

![image](https://github.com/user-attachments/assets/17e83d52-e6fb-41a3-aa3e-310fd2b23d54)





***
SQL
---

```
CREATE TABLE `Schedule` (
	`id`	int	NOT NULL,
	`UserName`	VARCHAR	NOT NULL,
	`Password`	VARCHAR	NOT NULL,
	`Title`	VARCHAR	NOT NULL,
	`Content`	VARCHAR	NULL,
	`CreateDate`	TIMESTAMP	NULL,
	`UpdateDate`	TIMESTAMP	NULL
);

ALTER TABLE `Schedule` ADD CONSTRAINT `PK_SCHEDULE` PRIMARY KEY (
	`id`
);


```
