let shipMap = {};
let previous_element = undefined;

function fetchShipListClass(c) {
    if (previous_element !== undefined)
        previous_element.classList.toggle("option");
    event.target.classList.toggle("option");
    previous_element = event.target;
    if (shipMap[c] === undefined) {
        $.ajax({
            type: 'GET',
            url: "/ship_list/class/"+c,
            success: function (data) {
                shipMap[c] = data;
                $('#icon-list-box').html(data);
            },
            error: function (xhr, status, error) {
                console.error(error);
            }
        });
    }
    else $('#icon-list-box').html(shipMap[c]);
}

function fetchShipListRarity(r) {
    if (shipMap[r] === undefined) {
        $.ajax({
            type: 'GET',
            url: "/ship_list/rarity/"+r,
            success: function (data) {
                shipMap[r] = data;
                $('#icon-list-box').html(data);
            },
            error: function (xhr, status, error) {
                console.error(error);
            }
        });
    }
    else $('#icon-list-box').html(shipMap[r]);
}

let class_search = document.getElementById("class-search-button");
let rarity_search = document.getElementById("rarity-search-button");

let class_option = document.getElementById("search-class");
let rarity_option = document.getElementById("search-rarity");
let empty_option = document.getElementById("search-holder");

class_search.addEventListener("click", function() {
    class_search.style.opacity = 0.3;
    rarity_search.style.opacity = 1.0;
    class_option.style.display = "block";
    rarity_option.style.display = "none";
    empty_option.style.display = "none";
});
rarity_search.addEventListener("click", function(){
    rarity_search.style.opacity = 0.3;
    class_search.style.opacity = 1.0;
    rarity_option.style.display = "block";
    class_option.style.display = "none";
    empty_option.style.display = "none";
});