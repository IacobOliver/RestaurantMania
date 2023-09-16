const puppeteer = require("puppeteer");
const fs = require("fs");
(async () => {
  // Launch a headless browser
  const browser = await puppeteer.launch({ headless: "new" });

  // Create a new page
  const page = await browser.newPage();

  // Navigate to the Glovo website
  await page.goto("https://tazz.ro/bucuresti/moon-bistro/17462/restaurant");

  // Perform actions such as searching for restaurants, clicking menu items, and extracting data here
  // ...
  // await page.waitForNavigation()

  let restaurant = await page.evaluate(() => {
    let obj = {
      address: "",
      description: "",
      name: document
        .querySelector(".tb_partner_name")
        .textContent.replaceAll("\n", "")
        .replaceAll("\t", ""),
      image: { imageUrl: document.querySelector(".restaurant_pic img").src },
    };
    const categList = document.querySelectorAll(".widget-container");
    let categs = [];

    for (let categ of categList) {
      categObj = { products: [] };
      categObj.name = categ.querySelector(".widget-title")?.textContent;
      if (categObj.name == null) continue;
      productsList = categ.querySelectorAll(".restaurant-product-card  ");
      for (let prod of productsList) {
        prodObj = {
          productDescription: prod
            .querySelector("p")
            .textContent.replaceAll("\n", ""),
          name: prod
            .querySelector(".title-container")
            .textContent.split("\n")[0],
          price: parseFloat(
            prod
              .querySelector(".product-price")
              ?.textContent.split("  ")
              .join(".")
          ),
          image: { imageUrl: prod.querySelector(".img-product")?.src },
        };
        if (prodObj.price === null) prodObj.price = parseFloat("0.00");

        categObj.products.push(prodObj);
      }
      categs.push(categObj);
    }
    obj.menu = categs;
    return obj;
  });

  // console.log(menuItems);

  // Close the browser when finished
  await browser.close();
  const jsonData = JSON.stringify(restaurant, null, 2);
  const filePath = "data.json";
  fs.writeFileSync(filePath, jsonData);
})();
