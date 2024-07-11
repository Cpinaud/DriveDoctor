document.addEventListener('DOMContentLoaded', function() {
    const navContainer = document.querySelector('.nav');
    const navLinks = document.querySelectorAll('.nav-link');

    function setActiveLink() {
        const currentPath = window.location.pathname;
        navLinks.forEach(link => {
            const linkPath = new URL(link.href).pathname;
            link.classList.toggle('active', linkPath === currentPath);
        });
    }

    setActiveLink();

    navContainer.addEventListener('click', function(event) {
        if (event.target.classList.contains('nav-link')) {
            navLinks.forEach(nav => nav.classList.remove('active'));
            event.target.classList.add('active');
        }
    });
});
