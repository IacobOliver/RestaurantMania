/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}",  "./node_modules/flowbite/**/*.jsx"],
  theme: {
    extend: {
      colors:{
      tc1: "#111315",
      tc2:"#EE6C23",
      // tc2:"#226BBF",
      tc2_2:"#FCD064",
      // tc2_2:"#4BC0F5",
      tc3:"#080808",
      hc1:"#a16207",
      },
      height:{
        "1/10":"20%",
        "30":"7.5rem"
      },
      screens: {
        "sm-400" :  {'max': '400px'}
      }
      
    },
  },
  plugins: [require("daisyui"),
            require('flowbite/plugin'),
            require('tailwindcss-animated')],
};

// !! cheile duplicate inseamna fonturi diferite

