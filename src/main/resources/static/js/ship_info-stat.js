let lv_buttons = document.getElementsByClassName("stat-lv-button");
let lv_header = document.getElementById("lv-title-header");
let stat_headers = document.getElementsByClassName("stat-header");
let stat_info = document.getElementsByClassName("stat-info");
let current_button = 0;
let stat125 = false;

let stat120_map = new Map();
let stat125_map = new Map();

//stat120 is already rendered, getting from the html content
for (var j = 0; j < stat_headers.length; j++) {
    stat120_map.set(stat_headers[j].textContent, stat_info[j].textContent);
}

let click_class_switch = "switch-stat-lv";

lv_buttons[0].addEventListener('click', () => {
    if (current_button != 0) {
        changeButton();
        lv_header.textContent = "120";
        for (var j = 0; j < stat_headers.length; j++)
            stat_info[j].textContent = stat120_map.get(stat_headers[j].textContent);
        current_button = 0;
    }
});

lv_buttons[1].addEventListener('click', () => {
    if (current_button != 1) {
        changeButton();
        lv_header.textContent = "125";
        if (!stat125) {
            $.getJSON('/ship_list/stat125/'+window.ship_id, function(data) {
                if (data !== null) {
                    const dataArray = Object.entries(data);
                    stat125_map = new Map(dataArray);
                    for (var j = 0; j < stat_headers.length; j++)
                        stat_info[j].textContent = stat125_map.get(stat_headers[j].textContent);
                }
            });
            stat125 = true;
        }
        for (var j = 0; j < stat_headers.length; j++)
            stat_info[j].textContent = stat125_map.get(stat_headers[j].textContent);
        current_button = 1;
    }
});

function changeButton() {
    lv_buttons[0].classList.toggle(click_class_switch);
    lv_buttons[1].classList.toggle(click_class_switch);
}

