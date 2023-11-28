let collapse_button = document.getElementById('info-collapse-button');
let info_panel = document.getElementById('right-info');
let ship_image = document.getElementById('ship-image');
let ship_skin_image = document.getElementById('ship-image-skin');

let switch_bg = true;
let first_switch = true;

collapse_button.addEventListener('click', () => {
    info_panel.classList.toggle('collapsing-info');
    collapse_button.classList.toggle('collapsing-icon');
    ship_image.classList.toggle('switching_image_bg');
    ship_skin_image.classList.toggle('switching_image_bg');
});

let skinbutton = document.getElementById("skin-button");
let skindisplay = document.getElementById("skin-option-container");

overlay = document.getElementById("overlay-layer");
overlay.addEventListener("click", () => {
    overlay.style.display = "none";
    skindisplay.style.display = "none";
});

skinbutton.addEventListener("click", () => {
    // display = skindisplay.style.display;
    // skindisplay.style.display = (display == "flex") ? "none" : "flex";
    // overlay.style.display = "block";
    if (switch_bg) {
        if (first_switch) {
            first_switch = !first_switch;
            fetch("/ship_list/image/"+window.ship_id)
                .then(response => response.blob())
                .then(blob => {
                    const objectURL = URL.createObjectURL(blob);
                    ship_skin_image.src = objectURL;
                    switchShipImage();
                })
                .catch(error => console.error('Error fetching image:', error));
        }
        else
            switchShipImage();
    }
    else
        switchShipImage();
    switch_bg = !switch_bg;
});

function switchShipImage() {
    if (ship_skin_image.style.zIndex == '-1') {
        ship_skin_image.style.zIndex = '0';
        ship_image.style.display = 'none';
    }
    else {
        ship_skin_image.style.zIndex = '-1';
        ship_image.style.display = 'block';
    }
}