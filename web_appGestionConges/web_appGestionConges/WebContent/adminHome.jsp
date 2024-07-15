<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
        .card {
            margin: 20px 0;
        }
        .header {
            margin-bottom: 40px;
        }
        .card-icon {
            font-size: 2rem;
            color: #007bff;
        }
        .card-body {
            display: flex;
            align-items: center;
        }
        .card-body > div {
            flex: 1;
        }
        .card-body > .text {
            flex: 4;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header text-center">
            <h2>Welcome, Admin</h2>
            <p>Manage the system from here</p>
        </div>
        <div class="card">
            <div class="card-body">
                <div>
                    <i class="fas fa-calendar-alt card-icon"></i>
                </div>
                <div class="text">
                    <h5 class="card-title">Manage Conges</h5>
                    <p class="card-text">View and manage all the conges requests.</p>
                </div>
                <div>
                    <a href="ConsulterConges.jsp" class="btn btn-primary">Manage Conges</a>
                </div>
            </div>
        </div>
        <div class="card">
            <div class="card-body">
                <div>
                    <i class="fas fa-users card-icon"></i>
                </div>
                <div class="text">
                    <h5 class="card-title">View Employees</h5>
                    <p class="card-text">View the list of all employees.</p>
                </div>
                <div>
                    <form action="ViewEmployeesServlet" method="get">
                        <button type="submit" class="btn btn-primary">View Employees</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
