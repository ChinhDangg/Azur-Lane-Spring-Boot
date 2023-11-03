let skill_buttons = document.getElementsByClassName("skill_num");
let current_active_skill = 0;

let skill_num = document.getElementById("skill-num-header");
let skill_name = document.getElementById("skill-title-header");
let skill_icon = document.getElementById("skill-icon");
let skill_desc = document.getElementById("skill-description");

let skillMap = new Map();
//skill1 is already rendered, getting from the html content
//and add it the skill list
const skill1 = {
    skill_name: skill_name.textContent,
    skill_description: skill_desc.textContent
}
skillMap.set(0, skill1);

let fate_button = document.getElementById("fate-button");
let switch_icon = document.getElementById("switch-icon");
let fate_text = document.getElementById("fate-text");
let fate_container = document.getElementById("fate-skill-container");
let fate_skill_title = document.getElementById("fate-skill-title-header");
let default_skill_title = document.getElementById("skill-title-header");
let fate_skill_desc = document.getElementById("fate-skill-description");

let fate_switch_container = "switching-fate-container";
let fate_switch_title = "switching-fate-title";
let default_switch_title = "switching-default-title";

let skill_with_fate = new Map();
//when skill1 has fate, get it from html content as it is already rendered 
//and add it to the fate skill list
if (fate_button.style.display != 'none') {
    const fate_skill1 = {
        skill_name: fate_skill_title.textContent,
        skill_description: fate_skill_desc.textContent
    }
    skill_with_fate.set(0, fate_skill1);
}

fate_button.addEventListener("click", () => {
    switchFateButton();
    fate_container.classList.toggle(fate_switch_container);
    fate_skill_title.classList.toggle(fate_switch_title);
    default_skill_title.classList.toggle(default_switch_title);
});

function displaySkill(num, name, desc) {
    skill_buttons[current_active_skill].style.backgroundColor = "#444";
    skill_buttons[num].style.backgroundColor = "#666";
    skill_num.textContent = 'Skill ' + (num+1);
    skill_name.textContent = name;
    skill_desc.textContent = desc;
    if (skill_with_fate.get(num) !== undefined) {
        fate_button.style.display = 'inline-block';
        data = skill_with_fate.get(num);
        fate_skill_title.textContent = data.skill_name;
        fate_skill_desc.textContent = data.skill_description;
    }
    else {
        fate_button.style.display = "none";
        if (!switch_icon.classList.contains("switching"))
            switchFateButton();
        if (fate_container.classList.contains(fate_switch_container)) {
            fate_container.classList.toggle(fate_switch_container);
            fate_skill_title.classList.toggle(fate_switch_title);
            default_skill_title.classList.toggle(default_switch_title);
        }
    }
}

function switchFateButton() {
    switch_icon.classList.toggle("switching");
    fate_text.classList.toggle("switching-text");
}

function clickSkillButton(event) {
    const index = Array.from(skill_buttons).indexOf(event.target);
    if (current_active_skill != index) {
        if (skillMap.get(index) !== undefined) {
            data = skillMap.get(index);
            displaySkill(index, data.skill_name, data.skill_description);
            current_active_skill = index;
        }
        else {
            $.getJSON('/ship_list/skill/'+window.ship_id+'/'+(index+1), function(data) {
                if (data !== null) {
                    non_fate = (data.length > 1 && data[0].skill_name.includes("Fate") ? 1 : 0)
                    if (data.length > 1) {
                        fate = (non_fate == 0) ? 1 : 0;
                        skill_with_fate.set(index, data[fate])
                        console.log(index);
                    }
                    skillMap.set(index, data[non_fate]);
                    displaySkill(index, data[non_fate].skill_name, data[non_fate].skill_description);
                    current_active_skill = index;
                }  
            });
        }
    }
}

Array.from(skill_buttons).forEach(function(element) {
    element.addEventListener('click', clickSkillButton);
});

