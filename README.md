# 📄 Lumen Reader API - SaaS

Welcome to **Lumen Reader API**, a SaaS solution for document processing. This API allows users to upload documents, extract data, check for signatures, and more. The service is available via subscription, providing scalable and reliable document processing capabilities. 🚀

## 🌟 Features

✅ **Document Parsing** - Extract structured data from various document formats (PDF, DOCX, etc.).  
✅ **Signature Detection** - Verify if a document is signed or not.  
✅ **OCR Support** - Process scanned documents and convert them into text.  
✅ **Secure API** - Authentication and role-based access.  
✅ **Subscription-Based** - Users must subscribe to access the API.  
✅ **Webhooks** - Receive real-time notifications for processed documents.  
✅ **Logging & Monitoring** - Track API usage and performance.

---

## 📌 Table of Contents
- [📄 API Documentation](#api-documentation)
- [🚀 Getting Started](#getting-started)
- [🔐 Authentication](#authentication)
- [📤 Uploading Documents](#uploading-documents)
- [📥 Retrieving Processed Data](#retrieving-processed-data)
- [📊 Subscription & Billing](#subscription--billing)
- [📡 Webhooks](#webhooks)
- [⚙️ Configuration](#configuration)
- [🛠️ Deployment](#deployment)
- [❓ FAQ](#faq)
- [📞 Support](#support)

---

## 📄 API Documentation
The complete API documentation is available via Swagger UI:
```
[BASE_URL]/swagger-ui/index.html
```
Or access the OpenAPI JSON file:
```
[BASE_URL]/v3/api-docs
```

---

## 🚀 Getting Started

### 1️⃣ Prerequisites
- Java 21+
- Spring Boot 3+
- PostgreSQL
- Docker (optional, for containerized deployment)

### 2️⃣ Installation
Clone the repository:
```sh
git clone https://github.com/your-repo/document-reader-api.git
cd document-reader-api
```

### 3️⃣ Environment Variables
Create a `.env` file and configure the following:
```env
DATABASE_URL=jdbc:postgresql://localhost:5432/doc_reader
DATABASE_USER=your_user
DATABASE_PASSWORD=your_password
JWT_SECRET=your_jwt_secret
STRIPE_SECRET_KEY=your_stripe_secret_key
```

### 4️⃣ Run the Application
```sh
./mvnw spring-boot:run
```
The API will be available at: `http://localhost:8080`

---

## 🔐 Authentication
This API uses **JWT authentication**. To use the API, first register and log in to obtain a token.

### 🔹 Register
```sh
POST /api/auth/register
```
#### Request Body
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "securepassword"
}
```

### 🔹 Login
```sh
POST /api/auth/login
```
#### Response
```json
{
  "token": "your.jwt.token"
}
```
Include this token in the `Authorization` header for all requests:
```sh
Authorization: Bearer your.jwt.token
```

---

## 📤 Uploading Documents
To process a document, send a **multipart/form-data** request:
```sh
POST /api/documents/upload
```
#### Request Example
```sh
curl -X POST "http://localhost:8080/api/documents/upload" \
  -H "Authorization: Bearer your.jwt.token" \
  -F "file=@path/to/document.pdf"
```

---

## 📥 Retrieving Processed Data
Once the document is processed, retrieve extracted data:
```sh
GET /api/documents/{documentId}
```
#### Example Response
```json
{
  "documentId": "12345",
  "status": "processed",
  "signatureDetected": true,
  "extractedText": "This is the extracted text."
}
```

---

## 📊 Subscription & Billing
Users must subscribe to a plan before using the API. The system integrates with **Stripe** for secure payments.

### 🔹 Subscribe to a Plan
```sh
POST /api/subscription/subscribe
```
#### Request Body
```json
{
  "plan": "premium"
}
```
#### Plans Available
- `free` - Limited requests per month.
- `standard` - Medium quota.
- `premium` - Unlimited access.

---

## 📡 Webhooks
Webhooks allow users to receive real-time updates when documents are processed.

### 🔹 Setup Webhook
```sh
POST /api/webhooks/register
```
#### Request Body
```json
{
  "url": "https://your-app.com/webhook-endpoint"
}
```

---

## ⚙️ Configuration
Some key settings:
- **Logging** - Configured via `application.yml`.
- **Database** - PostgreSQL, MySQL, or SQLite support.
- **Security** - JWT authentication and API key restrictions.

---

## 🛠️ Deployment
Deploy using **Docker**:
```sh
docker-compose up -d
```
Or manually using a Java environment:
```sh
java -jar target/document-reader-api.jar
```

---

## ❓ FAQ
### 🔹 How do I reset my password?
Use the endpoint:
```sh
POST /api/auth/reset-password
```

### 🔹 What file formats are supported?
Currently, **PDF**, **DOCX**, and **TXT** files.

### 🔹 Can I self-host this API?
Yes! You can deploy it using your own infrastructure.

---

## 📞 Support
For issues and inquiries, contact us at:
📧 **support@documentreaderapi.com**  
📌 [GitHub Issues](https://github.com/your-repo/document-reader-api/issues)

---

## ⭐ Contributing
Contributions are welcome! Fork the repository, create a branch, and submit a PR.

---

## 📜 License
This project is licensed under the MIT License.

---

🚀 **Happy coding!** 🚀

