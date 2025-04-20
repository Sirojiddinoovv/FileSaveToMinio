
# FileMinio

Проект `fileMinio` — это Spring Boot приложение, предназначенное для работы с файлами, возможно, с использованием MinIO или аналогичного S3-хранилища.

## 🔧 Технологии

- Java 22
- Spring Boot
- Maven
- MinIO / S3 API
- Lombok

## 🚀 Быстрый старт

### Клонирование проекта

```bash
git clone https://github.com/your-username/fileMinio.git
cd fileMinio
```

### Сборка проекта

```bash
./mvnw clean install
```

### Запуск приложения

```bash
./mvnw spring-boot:run
```

Приложение будет доступно по адресу: `http://localhost:8705`

## ⚙️ Конфигурация

Настройки приложения указываются в `application-local.yml`. Важно указать данные для подключения к MinIO:

```yaml
minio:
  url: http://localhost:9000
  access-key: YOUR_ACCESS_KEY
  secret-key: YOUR_SECRET_KEY
  bucket: your-bucket-name
```

## 📂 Структура проекта

- `controller/` — REST-контроллеры
- `service/` — бизнес-логика
- `model/` — DTO и Entity классы
- `config/` — конфигурационные классы
- `util/` — вспомогательные утилиты

## 📦 Maven зависимости

Основные зависимости (см. `pom.xml`):

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
  <groupId>io.minio</groupId>
  <artifactId>minio</artifactId>
  <version>8.5.3</version>
</dependency>
<!-- и другие -->
```

## 🧪 Тестирование

```bash
./mvnw test
```

## 📝 Лицензия

Добавьте здесь информацию о лицензии (например MIT, Apache 2.0 и т.д.)

---

> Проект создан для демонстрации взаимодействия с файловым хранилищем через Spring Boot.

