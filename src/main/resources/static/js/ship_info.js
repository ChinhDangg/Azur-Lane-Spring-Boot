let collapse_button = document.getElementById('info-collapse-button');
let info_panel = document.getElementById('right-info');
let collapse_class = 'collapsing-info';

collapse_button.addEventListener('click', () => {
    info_panel.classList.toggle(collapse_class);
    collapse_button.classList.toggle(collapse_class);
});