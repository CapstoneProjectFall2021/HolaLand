/*!
 * js
 */

let theme = localStorage.getItem('data-theme');
const checkbox = document.getElementById("switch");

const changeThemeToDark = () => {
  document.documentElement.setAttribute("data-theme", "dark");
  localStorage.setItem("data-theme", "dark");
  checkbox.checked = true;
}

const changeThemeToLight = () => {
  document.documentElement.setAttribute("data-theme", "light");
  localStorage.setItem("data-theme", 'light');
  checkbox.checked = false;
}

if (theme === 'dark') {
  changeThemeToDark();
}

checkbox.addEventListener('change', () => {
  let theme = localStorage.getItem('data-theme');
  if (theme === 'dark') {
    changeThemeToLight();
  } else {
    changeThemeToDark();
  }
});

var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
  var tooltip = new bootstrap.Tooltip(tooltipTriggerEl);
  tooltip.show();
})