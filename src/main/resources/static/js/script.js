// script.js
document.addEventListener('DOMContentLoaded', () => {
    // Theme handling
    const themeToggleButtons = document.querySelectorAll('.theme-toggle, #darkModeToggle');
    const currentTheme = localStorage.getItem('theme') || 'light';

    // Set initial theme
    document.body.setAttribute('data-theme', currentTheme);

    // Update button state
    const updateButton = () => {
        const isDark = document.body.getAttribute('data-theme') === 'dark';
        themeToggleButtons.forEach(button => {
            button.textContent = isDark ? '🌞' : '🌓';
        });
    };

    // Toggle theme function
    const toggleTheme = () => {
        const currentTheme = document.body.getAttribute('data-theme');
        const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
        
        document.body.setAttribute('data-theme', newTheme);
        localStorage.setItem('theme', newTheme);
        updateButton();
    };

    // Add event listeners
    themeToggleButtons.forEach(button => {
        button.addEventListener('click', toggleTheme);
    });

    // Initialize button state
    updateButton();
});