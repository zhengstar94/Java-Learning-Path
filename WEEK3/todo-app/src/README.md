# Todo App (Spring Boot + JPA + PostgreSQL/Neon)

这是一个用于练习 **Spring Boot + Spring Data JPA + PostgreSQL** 的 Todo REST API 项目。  
数据库使用 **Neon（远程临时 PostgreSQL）**，实现数据持久化：应用重启后数据仍然存在。

---

## 功能概览

- `GET /ping`：健康检查
- `POST /tasks`：创建任务
- `GET /tasks`：查询全部任务
- `PUT /tasks/{id}`：更新任务（标题/完成状态）
- `DELETE /tasks/{id}`：删除任务

---

## 技术栈

- Java + Spring Boot
- Spring Web
- Spring Data JPA (Hibernate)
- PostgreSQL（Neon）
- VS Code + REST Client（`.http` 文件测试 API）

---

## 本地运行前置条件

- Java 17（或你项目生成时选择的版本）
- VS Code（可选：安装 REST Client 插件）
- 可以访问 Neon 数据库（网络允许连接）

---

## 配置数据库连接（重要：不要把密码提交到 GitHub）

推荐使用环境变量来配置数据库连接信息。

### 方式 A：环境变量（推荐）

在终端（项目根目录）执行：

```bash
export DB_URL="jdbc:postgresql://<HOST>:5432/<DB>?sslmode=require&channel_binding=require"
export DB_USER="<USER>"
export DB_PASS="<PASSWORD>"

./mvnw spring-boot:run
```

然后访问：

* `http://localhost:8080/ping`

### application.properties（提交到仓库的安全版本）

确保仓库中的配置是占位符形式：

```properties
spring.application.name=todo-app

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> `ddl-auto=update`：开发练习用，自动建表/更新表结构。

---

## 启动项目

```bash
./mvnw spring-boot:run
```

---

## 使用 VS Code 测试 API（不使用 Postman）

推荐安装 VS Code 插件：**REST Client**。
项目根目录提供 `api.http`，打开后点击每个请求上方的 `Send Request` 即可调用。

示例（`api.http`）：

```http
@baseUrl = http://localhost:8080

### Ping
GET {{baseUrl}}/ping

### Create task
POST {{baseUrl}}/tasks
Content-Type: application/json

{
  "title": "learn jpa"
}

### List tasks
GET {{baseUrl}}/tasks

### Update task
PUT {{baseUrl}}/tasks/1
Content-Type: application/json

{
  "completed": true
}

### Delete task
DELETE {{baseUrl}}/tasks/1
```

---

## 持久化验证（第三周验收点）

1. 启动项目
2. `POST /tasks` 创建几条数据
3. `GET /tasks` 确认存在
4. 停掉应用（Ctrl+C）
5. 再次启动项目
6. 再 `GET /tasks`
   ✅ 如果数据仍在，说明持久化成功。

---

## 项目结构参考

```
src/main/java/com/example/todoapp
  ├─ TodoAppApplication.java
  ├─ HelloController.java
  └─ task
     ├─ Task.java              (@Entity)
     ├─ TaskRepository.java    (JpaRepository)
     ├─ TaskService.java       (Service 层，可选但推荐)
     └─ TaskController.java
```

---

## 安全提示

* 不要将 Neon 的真实连接串/密码提交到 GitHub
* 如果你曾公开暴露过连接串，建议去 Neon 控制台对 role 密码进行 **rotate/reset**

---

