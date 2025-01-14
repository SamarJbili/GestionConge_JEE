<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>




<div class="navbar bg-base-100 border-slate-800	">
	<div class="flex-1">
		<label for="my-drawer-2"
			class="btn btn-primary drawer-button lg:hidden"> <i
			class="fas fa-bars"></i> <!-- Font Awesome bars icon -->
		</label> <a class="btn btn-ghost text-xl" href="#">LeavEase</a>
	</div>


	<label class="swap swap-rotate"> <input id="themeToggle"
		type="checkbox" style="display: none;" checked /> <svg
			class="swap-on fill-current w-10 h-10"
			xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
			<path
				d="M5.64,17l-.71.71a1,1,0,0,0,0,1.41,1,1,0,0,0,1.41,0l.71-.71A1,1,0,0,0,5.64,17ZM5,12a1,1,0,0,0-1-1H3a1,1,0,0,0,0,2H4A1,1,0,0,0,5,12Zm7-7a1,1,0,0,0,1-1V3a1,1,0,0,0-2,0V4A1,1,0,0,0,12,5ZM5.64,7.05a1,1,0,0,0,.7.29,1,1,0,0,0,.71-.29,1,1,0,0,0,0-1.41l-.71-.71A1,1,0,0,0,4.93,6.34Zm12,.29a1,1,0,0,0,.7-.29l.71-.71a1,1,0,1,0-1.41-1.41L17,5.64a1,1,0,0,0,0,1.41A1,1,0,0,0,17.66,7.34ZM21,11H20a1,1,0,0,0,0,2h1a1,1,0,0,0,0-2Zm-9,8a1,1,0,0,0-1,1v1a1,1,0,0,0,2,0V20A1,1,0,0,0,12,19ZM18.36,17A1,1,0,0,0,17,18.36l.71.71a1,1,0,0,0,1.41,0,1,1,0,0,0,0-1.41ZM12,6.5A5.5,5.5,0,1,0,17.5,12,5.51,5.51,0,0,0,12,6.5Zm0,9A3.5,3.5,0,1,1,15.5,12,3.5,3.5,0,0,1,12,15.5Z" /></svg>
		</svg> <svg class="swap-off fill-current w-10 h-10"
			xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
			<path
				d="M21.64,13a1,1,0,0,0-1.05-.14,8.05,8.05,0,0,1-3.37.73A8.15,8.15,0,0,1,9.08,5.49a8.59,8.59,0,0,1,.25-2A1,1,0,0,0,8,2.36,10.14,10.14,0,1,0,22,14.05,1,1,0,0,0,21.64,13Zm-9.5,6.69A8.14,8.14,0,0,1,7.08,5.22v.27A10.15,10.15,0,0,0,17.22,15.63a9.79,9.79,0,0,0,2.1-.22A8.11,8.11,0,0,1,12.14,19.73Z" /></svg>
		</svg>
	</label> <a class="btn btn-neutral mx-1 mr-3" href="/web_appGestionConges">Home</a>

	<a class="btn mr-2 btn-primary"
		href="/web_appGestionConges/EmployeeServlet">Dashboard</a>


	<div class="flex-none">
		<div class="dropdown dropdown-end">
			<div tabindex="0" role="button" class="btn btn-ghost btn-circle">
				<div class="indicator">
					<svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none"
						viewBox="0 0 24 24" stroke="currentColor">
						<path stroke-linecap="round" stroke-linejoin="round"
							stroke-width="2"
							d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" /></svg>
					<span class="badge badge-sm indicator-item">x</span>
				</div>
			</div>
			<div tabindex="0"
				class="mt-3 z-[1] card card-compact dropdown-content w-52 bg-base-100 shadow">
				<div class="card-body">
					<div id="notifications-container"></div>


				</div>
			</div>
		</div>
		<div class="dropdown dropdown-end">
			<div tabindex="0" role="button"
				class="btn btn-ghost btn-circle avatar">
				<div class="w-10 rounded-full">
					<img alt="Tailwind CSS Navbar component"
						src="https://www.mydevify.com/assets/index.494ac568.png" />
				</div>
			</div>
			<ul tabindex="0"
				class="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-52">
				<li><a class="justify-between" href="#"> Profile <span
						class="badge">New</span>
				</a></li>
				<li><a href="#">Settings</a></li>
				<li>
					<form action="logout" method="get">
						<button type="submit">Logout</button>
					</form>
				</li>
			</ul>
		</div>
	</div>




</div>

</div>



<script>
  function fetchNotifications() {
    fetch('/fetch-notifications/')
        .then(response => response.json())
        .then(data => {
            const notificationsContainer = document.getElementById('notifications-container');
            notificationsContainer.innerHTML = '<span class="font-bold text-lg">Notifications:</span><br>';
            if (data.notifications.length === 0) {
                const noNotifications = document.createElement('span');
                noNotifications.textContent = 'No notifications';
                notificationsContainer.appendChild(noNotifications);
            } else {
                data.notifications.forEach(notification => {
                    const span = document.createElement('span');
                    span.classList.add('text-info');
                    if (notification.link) {
                        const link = document.createElement('a');
                        link.href = notification.link;
                        link.target = '_blank'; 
                        link.textContent = notification.message;
                        span.appendChild(link);
                    } else {
                        span.textContent = notification.message;
                    }
                    notificationsContainer.appendChild(span);
                });
            }
        })
        .catch(error => console.error('Error fetching notifications:', error));
}

window.onload = fetchNotifications;

</script>

<script>

// JavaScript code
document.addEventListener('DOMContentLoaded', function() {
    const themeToggle = document.getElementById('themeToggle');

    // Check the local storage for the theme preference
    const isDarkTheme = localStorage.getItem('theme') === 'dark';

    // Set the initial theme based on the preference
    if (isDarkTheme) {
        document.body.classList.add('dark');
    } else {
        document.body.classList.remove('dark');
    }

    // Set the 'checked' attribute of the theme toggle input based on the theme preference
    themeToggle.checked = isDarkTheme;

    // Listen for changes in the theme toggle
    themeToggle.addEventListener('change', function() {
        if (this.checked) {
            document.body.classList.add('dark');
            localStorage.setItem('theme', 'dark');
        } else {
            document.body.classList.remove('dark');
            localStorage.setItem('theme', 'light');
        }
    });
});


</script>