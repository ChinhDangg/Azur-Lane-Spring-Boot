let augment_icon_container = document.getElementById('augment-icon-container');
let augment_info_container = document.getElementById('augment-info-container');
let augment_collapse_button = document.getElementById('augment-collapse-button');
let augment_icon_switching = 'switching-augment-icon-container';
let gear_header_title = document.getElementById('gear-header-title');

let switch_augment = true;

augment_icon_container.addEventListener('click', () => {
    if (switch_augment) {
        augment_icon_container.classList.add(augment_icon_switching);
        augment_info_container.style.display = 'block';
        gear_header_title.innerText = 'Augment';
        switch_augment = false;
    }
    else {
        augment_icon_container.classList.remove(augment_icon_switching);
        augment_info_container.style.display = 'none';
        gear_header_title.innerText = 'Gear';
        switch_augment = true;
    }
});