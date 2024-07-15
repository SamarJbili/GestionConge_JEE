<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    // Assuming you have a userType attribute in your session
    String userType = (String) session.getAttribute("type");

    // Check if the user is staff
    boolean isStaff = "ADMIN".equals(userType);
%>
<div class="sticky top-0 z-50">
  <div class="drawer lg:drawer-open">
    <input id="my-drawer-2" type="checkbox" class="drawer-toggle" />
    <div class="drawer-content flex flex-col items-center justify-center">
      
    </div> 
    <div class="drawer-side">
      <label for="my-drawer-2" aria-label="close sidebar" class="drawer-overlay"></label> 
      <ul class="menu p-4 w-80 min-h-full bg-base-200 text-base-content">
        <!-- Sidebar content here -->
        <ul class="menu bg-base-200 w-56 rounded-box">
          
          
          <% if (isStaff) { %>
          <li>
            <h2 class="menu-title">Staff Area</h2>
            <ul>
            
             <a href="http://localhost:8180/web_appGestionConges/AdminHome.jsp"> 

                <li><span>Admin Control Panel</span></li>
              </a>
            
              <a href="http://localhost:8180/web_appGestionConges/employees.jsp"> 

                <li><span>Employees</span></li>
              </a>
              <a href="http://localhost:8180/web_appGestionConges/ConsulterConges.jsp"> 

                <li ><span>Manage Leaves</span></li>
              </a>   
              
                         

            </ul>
          </li><% } else { %>
    <p></p>
<% } %>
          
      
          <li>
            <h2 class="menu-title">Posts</h2>
            <ul>
              <a href="requestConge.jsp"> 

                <li><span>Create New Post</span></li>
              </a>
             
               <form action="ConsulterCongeUserServlet" method="GET">
                  <button type="submit" >Consulter Votre Conge</button>
              </form>
            </ul>
          </li>


        </ul>
      </ul>
    </div>
  </div>
</div>

<script>
  document.addEventListener('DOMContentLoaded', function() {
    const themeToggle = document.getElementById('themeToggle');
    const htmlElement = document.documentElement; // Get the <html> element

    const isDarkTheme = localStorage.getItem('theme') === 'dark';

    if (isDarkTheme) {
        htmlElement.setAttribute('data-theme', 'dark');
        document.body.classList.add('dark');
    } else {
        htmlElement.setAttribute('data-theme', 'light');
        document.body.classList.remove('dark');
    }

    themeToggle.checked = isDarkTheme;

    themeToggle.addEventListener('change', function() {
        if (this.checked) {
            htmlElement.setAttribute('data-theme', 'dark');
            document.body.classList.add('dark');
            localStorage.setItem('theme', 'dark');
        } else {
            htmlElement.setAttribute('data-theme', 'light');
            document.body.classList.remove('dark');
            localStorage.setItem('theme', 'light');
        }
    });
});

</script>
