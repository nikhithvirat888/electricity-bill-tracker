# Electricity Bill & Usage Tracker ⚡

A modern, deployment-ready full-stack web application designed to monitor electricity consumption, calculate tiered utility bills, audit domestic household appliances, and visualize power trends over time.

---

## 📋 Features

- **User Authentication & Profiles:** Secure registration and login using Spring Security and **BCrypt** password hashing. Profile management with consumer and meter registration.
- **Dynamic Energy Dashboard:**
  - Real-time display of current month units consumed (kWh) and estimated bill.
  - Previous month usage comparison with percentage increase/decrease indicators and smart alerts.
  - Interactive charts for **Monthly Electricity Usage (Bar Chart)** and **Monthly Bill Amount (Area Chart)** powered by Recharts.
  - Recent bill invoices and fast shortcuts.
- **Smart Meter Reading:**
  - Auto-fetches latest recorded dial readings.
  - Automatically calculates net units consumed (`currentReading - previousReading`).
  - Validation to prevent current reading being lower than previous reading.
  - Complete history table with Edit and Delete operations.
- **Bill Management & Dynamic Tiered Tariffs:**
  - Statement generation with consumer number, readings, units, bill amount, issue date, and due dates.
  - **Dynamic Multi-Slab Tariff Engine:** Configured in MySQL (`0–100`, `101–200`, `201–300`, `301+` kWh slabs + fixed grid charge) without hardcoding rates permanently.
  - One-click auto-calculation of bill amounts based on active tariff slabs.
  - Status tracking (`PAID`, `UNPAID`, `PENDING`).
  - Search by date/month and filter bills by payment status.
  - Detailed invoice inspection modal.
- **Appliance Consumption Calculator:**
  - Quick presets (Air Conditioner, Refrigerator, Ceiling Fan, LED TV, LED Bulbs, Geyser, Microwave, PC).
  - Formulas for Daily Consumption (`(W * Qty * Hours) / 1000`) and Monthly Consumption.
  - Real-time estimated monthly cost calculation via the backend tariff service.
  - Household audited devices table with total load summary.
- **Security & Validation:**
  - Passwords hashed using BCrypt.
  - Basic Authentication via Spring Security with JSON error entry point.
  - Cross-Origin Resource Sharing (CORS) configured with environment variable overrides.
  - Input validation on DTOs with global exception handler and formatted API responses.
- **Responsive UI:**
  - Collapsible sidebar navigation, header with user avatar and quick actions, stat cards, responsive charts, and modal dialogs.

---

## 🛠️ Tech Stack

### Frontend
- **Framework:** React.js 18 with Vite
- **Routing:** React Router v6
- **Charts:** Recharts
- **Icons:** Lucide React
- **Styling:** Custom Vanilla CSS (Modern design system, fully responsive)

### Backend
- **Framework:** Java 17, Spring Boot 3.2.5
- **ORM / Persistence:** Spring Data JPA / Hibernate
- **Security:** Spring Security with BCrypt Password Encoder
- **Validation:** Jakarta Bean Validation (Hibernate Validator)
- **Build Tool:** Apache Maven

### Database
- **Engine:** MySQL 8.0+ / 9.x
- **Driver:** MySQL Connector/J

---

## 📁 Project Structure

```text
electricity-bill-tracker/
├── .gitignore
├── README.md
├── client/                               # React Frontend
│   ├── .env                             # Environment variables (VITE_API_URL)
│   ├── .env.example
│   ├── index.html
│   ├── package.json
│   ├── vite.config.js
│   └── src/
│       ├── main.jsx                     # Vite React entry point
│       ├── App.jsx                      # App routes & auth guards
│       ├── index.css                    # Professional responsive stylesheet
│       ├── context/
│       │   └── AuthContext.jsx          # User state & session manager
│       ├── services/                    # API communication layer
│       │   ├── api.js                   # Universal fetch wrapper with Auth
│       │   ├── authService.js
│       │   ├── meterReadingService.js
│       │   ├── billService.js
│       │   ├── applianceService.js
│       │   ├── dashboardService.js
│       │   └── tariffService.js
│       ├── components/
│       │   ├── Sidebar.jsx
│       │   ├── Header.jsx
│       │   ├── Layout.jsx
│       │   ├── StatCard.jsx
│       │   └── ProtectedRoute.jsx
│       └── pages/
│           ├── Login.jsx
│           ├── Register.jsx
│           ├── Dashboard.jsx
│           ├── MeterReading.jsx
│           ├── AddBill.jsx
│           ├── BillHistory.jsx
│           ├── ApplianceCalculator.jsx
│           └── Profile.jsx
│
└── server/                               # Spring Boot Backend
    ├── pom.xml
    └── src/main/
        ├── resources/
        │   └── application.properties   # Database and CORS configurations
        └── java/com/electricity/
            ├── ElectricityBillTrackerApplication.java
            ├── config/                  # SecurityConfig, CustomUserDetailsService, SecurityUtils
            ├── controller/              # Auth, User, MeterReading, Bill, Appliance, Dashboard, Tariff
            ├── dto/                     # Requests, Responses, Summaries
            ├── entity/                  # User, MeterReading, Bill, Appliance, TariffSlab
            ├── exception/               # GlobalExceptionHandler, Custom exceptions, ErrorResponse
            ├── repository/              # Spring Data JPA Repositories
            └── service/                 # Business logic, Tariff calculation, Metrics aggregation
```

---

## 🗄️ MySQL Database Setup

1. Make sure MySQL is running on your system.
2. Connect to MySQL and create the database:
   ```sql
   CREATE DATABASE IF NOT EXISTS electricity_tracker;
   ```
3. Default database connection in `application.properties`:
   - Host: `localhost:3306`
   - Database: `electricity_tracker`
   - Username: `root`
   - Password: `root`
4. The application uses Hibernate `ddl-auto=update`, which automatically generates all tables (`users`, `meter_readings`, `bills`, `appliances`, `tariff_slabs`) upon server startup. Default tariff slabs are automatically pre-seeded.

---

## 🚀 How to Run the Application

### 1. Run the Spring Boot Backend

Open a terminal and navigate to the `server` folder:

```bash
cd electricity-bill-tracker/server
mvn spring-boot:run
```

*The backend will start at `http://localhost:8080`.*

> **Environment Variables (Optional overrides):**
> You can override default database parameters without changing source code:
> - `DB_URL` (e.g. `jdbc:mysql://my-cloud-db.com:3306/electricity_tracker`)
> - `DB_USERNAME` (e.g. `admin`)
> - `DB_PASSWORD` (e.g. `mySecurePassword`)
> - `CORS_ALLOWED_ORIGINS` (e.g. `http://localhost:5173,https://myproductionapp.com`)

### 2. Run the React Frontend

Open a new terminal and navigate to the `client` folder:

```bash
cd electricity-bill-tracker/client
npm install
npm run dev
```

*The frontend will start at `http://localhost:5173`.*

---

## 📡 REST API Documentation

### Authentication & Users
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `POST` | `/api/auth/register` | Register new electricity consumer | No |
| `POST` | `/api/auth/login` | Login and obtain basic auth token | No |
| `GET` | `/api/users/profile` | Get current user's profile | Yes |
| `PUT` | `/api/users/profile` | Update profile information | Yes |

### Meter Readings
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `GET` | `/api/meter-readings` | List all readings for current user | Yes |
| `GET` | `/api/meter-readings/latest` | Get latest recorded reading | Yes |
| `POST` | `/api/meter-readings` | Record new meter reading | Yes |
| `GET` | `/api/meter-readings/{id}` | Get specific meter reading | Yes |
| `PUT` | `/api/meter-readings/{id}` | Update meter reading | Yes |
| `DELETE` | `/api/meter-readings/{id}` | Delete meter reading | Yes |

### Utility Bills
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `GET` | `/api/bills` | List all bills for current user | Yes |
| `POST` | `/api/bills` | Create and save utility bill | Yes |
| `GET` | `/api/bills/{id}` | Get specific bill details | Yes |
| `PUT` | `/api/bills/{id}` | Update bill | Yes |
| `DELETE` | `/api/bills/{id}` | Delete bill | Yes |

### Appliance Calculator
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `GET` | `/api/appliances` | List audited appliances | Yes |
| `POST` | `/api/appliances` | Add appliance and compute load | Yes |
| `GET` | `/api/appliances/{id}` | Get specific appliance | Yes |
| `PUT` | `/api/appliances/{id}` | Update appliance | Yes |
| `DELETE` | `/api/appliances/{id}` | Remove appliance | Yes |

### Dashboard & Tariffs
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| `GET` | `/api/dashboard/summary` | Get aggregated metrics & charts | Yes |
| `GET` | `/api/tariffs` | List active electricity slabs | No / Yes |
| `POST` | `/api/tariffs/calculate?units=X` | Calculate bill breakdown for units | No / Yes |

---

## 🧪 Local Testing Steps

Follow these exact steps to test the application end-to-end:

1. **Verify Backend:**
   - Launch Spring Boot: `mvn spring-boot:run` in `electricity-bill-tracker/server`.
   - Ensure the server logs report: `Started ElectricityBillTrackerApplication in ... seconds (process running on port 8080)`.
2. **Verify Frontend:**
   - Launch Vite: `npm run dev` in `electricity-bill-tracker/client`.
   - Open browser at `http://localhost:5173`.
3. **Register an Account:**
   - Navigate to the Register page (`/register`).
   - Enter name (e.g. `Alex Turner`), email (`alex@example.com`), consumer number (`EB-77402`), and password (`password123`).
   - Click **Register Account** — you will be automatically signed in and redirected to the **Dashboard**.
4. **Log Meter Readings:**
   - Go to **Meter Reading** in the sidebar.
   - Enter `1000` for Previous Reading and `1250` for Current Reading. Notice the live preview shows `250.00 kWh`.
   - Click **Save Reading**. Check that it appears in the Reading History table.
5. **Add a Utility Bill:**
   - Go to **Add Bill**.
   - Input Previous Reading `1000` and Current Reading `1250`. Units consumed automatically updates to `250`.
   - Click **Auto-Calculate via Tariff Slabs** to inspect the tiered calculation breakdown.
   - Set status to `PENDING` and save the bill.
6. **Inspect Bill History:**
   - Open **Bill History**. Use the search bar to filter by date or status.
   - Click the Eye icon to view the bill invoice breakdown modal.
7. **Audit Appliances:**
   - Go to **Appliance Calculator**.
   - Click preset chips (e.g. *Air Conditioner*, *Refrigerator*, *Ceiling Fan*).
   - Click **Add Appliance** to calculate individual and total consumption loads.
8. **View Dashboard Trends:**
   - Return to **Dashboard**. Notice the consumption units, estimated bill amount, monthly usage bar chart, bill area chart, and trend badges are now populated in real time!

---

## 🚢 Deployment Instructions

### Frontend (Production Build)
1. In `client/`, set production backend URL in `.env.production`:
   ```bash
   VITE_API_URL=https://your-api-domain.com/api
   ```
2. Build optimized static assets:
   ```bash
   npm run build
   ```
3. Deploy the resulting `client/dist` folder to Vercel, Netlify, AWS S3/CloudFront, or Firebase Hosting.

### Backend (JAR Package)
1. In `server/`, build executable JAR:
   ```bash
   mvn clean package -DskipTests
   ```
2. The standalone JAR is created at `server/target/electricity-bill-tracker-server-1.0.0.jar`.
3. Run in production with environment variables:
   ```bash
   java -jar target/electricity-bill-tracker-server-1.0.0.jar \
     --DB_URL=jdbc:mysql://<production-host>:3306/electricity_tracker \
     --DB_USERNAME=<production-user> \
     --DB_PASSWORD=<production-password> \
     --CORS_ALLOWED_ORIGINS=https://your-frontend-domain.com
   ```
4. Deploy to AWS Elastic Beanstalk, Render, Railway, Heroku, or a Docker container.
