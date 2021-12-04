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

if (theme === null) {
  theme = 'dark';
}

if (theme === 'dark') {
  changeThemeToDark();
}

if (theme === 'light') {
  changeThemeToLight();
}

checkbox.addEventListener('change', () => {
  let theme = localStorage.getItem('data-theme');
  if (theme === 'dark') {
    changeThemeToLight();
  } else {
    changeThemeToDark();
  }
});

const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
const tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
  return new bootstrap.Tooltip(tooltipTriggerEl)
})

window.onscroll = function() {
  if (document.body.scrollTop > 50 || document.documentElement.scrollTop > 50) {
    document.getElementById("navbar").className = "navbar navbar-sm navbar-expand-lg fixed-top dark";
  } else {
    document.getElementById("navbar").className = "navbar navbar-expand-lg fixed-top";
  }
};