let collapse_button = document.getElementById('info-collapse-button');
let info_panel = document.getElementById('right-info');
let ship_image = document.getElementById('ship-image');

let switch_bg = true;

collapse_button.addEventListener('click', () => {
    info_panel.classList.toggle('collapsing-info');
    collapse_button.classList.toggle('collapsing-icon');
    ship_image.classList.toggle('switching_image_bg');
});

function hey() {
}