<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Request Conge</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
    <div class="container mx-auto p-6">
        <div class="max-w-md mx-auto bg-white rounded-xl shadow-md overflow-hidden md:max-w-2xl">
            <div class="md:flex">
                <div class="w-full p-4">
                    <h2 class="text-2xl font-bold text-gray-700 mb-4">Request a Conge</h2>
                    <form action="RequestCongeServlet" method="post" class="space-y-4">
                        <div>
                            <label for="description" class="block text-sm font-medium text-gray-700">Description:</label>
                            <input type="text" id="description" name="description" class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" required>
                        </div>
                        
                        <div>
                            <label for="dateDebut" class="block text-sm font-medium text-gray-700">Start Date:</label>
                            <input type="date" id="dateDebut" name="dateDebut" class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" required>
                        </div>
                        
                        <div>
                            <label for="dateFin" class="block text-sm font-medium text-gray-700">End Date:</label>
                            <input type="date" id="dateFin" name="dateFin" class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm placeholder-gray-400 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" required>
                        </div>
                        
                        <div>
                            <input type="submit" value="Request Conge" class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
