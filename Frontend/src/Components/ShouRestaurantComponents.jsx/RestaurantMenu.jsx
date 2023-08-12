import React from "react";
import { useState } from "react";
import CategoryAccordeon from "./CategoryAccordeon";

export default function RestaurantMenu({ thisRestaurant, setThisRestaurant }) {
  const [open, setOpen] = useState(1);
  const handleOpen = (value, e) => {
    if (e.target.nodeName != "I") setOpen(open === value ? 0 : value);
  };

  const addNewCateg = () => {
    fetch(
      `http://localhost:8080/categoryProduct/post/new/category/${thisRestaurant.id}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({}),
      }
    )
      .then((res) => res.json())
      .then((newCateg) => {
        console.log(newCateg);
        setThisRestaurant({
          ...thisRestaurant,
          menu: {
            ...thisRestaurant.menu,
            categoryProducts: [
              ...thisRestaurant.menu.categoryProducts,
              newCateg,
            ],
          },
        });
      })
      .catch((error) => {
        console.error("Error fetching data (categoryProducts):", error);
      });
  };
  const addNewProd = (categId) => {
    fetch(`http://localhost:8080/product/post/new/product/${categId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({}),
    })
      .then((res) => res.json())
      .then((newProd) => {
        console.log(newProd);
        let thisRestaurantCopy = { ...thisRestaurant };
        thisRestaurantCopy.menu.categoryProducts.map((categ) => {
          if (categ.id === categId) {
            categ.products.push(newProd);
          }
          return categ;
        });
        setThisRestaurant(thisRestaurantCopy);
      })
      .catch((error) => {
        console.error("Error fetching data (categoryProducts):", error);
        console.log("error in fetch");
      });
  };

  const editContentEvent = (contentRef, key, categId) => {
    let thisRestaurantCopy = { ...thisRestaurant };

    fetch(`http://localhost:8080/categoryProduct/update/${key}/${categId}`, {
      method: "PATCH",
      headers: {
        "Content-Type" : "application/json",
      },
      body: contentRef.current.textContent,
    }).then(res => res.json())
    .catch(err =>{
      console.err("Err at updating categName " + err)
    })

    


    for (let categ of thisRestaurantCopy.menu.categoryProducts) {
        if (categ.id === categId) {
            categ[key] = contentRef.current.textContent;
            break;
          }
    }
    setThisRestaurant(thisRestaurantCopy);

  };

  const editProduct = (contentRef, key, categId, prodId) => {
    let thisRestaurantCopy = { ...thisRestaurant };
    for (let categ of thisRestaurantCopy.menu.categoryProducts) {
        if (categ.id === categId) {
            for(let prod of categ.products){
                if(prod.id === prodId){
                    prod[key] = contentRef.current.textContent
                    break;
                }
            }
            break;
          }
    }
    setThisRestaurant(thisRestaurantCopy);
  };

  return (
    <>
      <div className="md:mr-2 rounded-t-lg divide-y-2 border-b-2 bg-gray-800 lg:overflow-visible">
        {thisRestaurant?.menu.categoryProducts.map((categ, index) => {
          return (
            <CategoryAccordeon
              open={open}
              index={index}
              handleOpen={handleOpen}
              categ={categ}
              editContentEvent={editContentEvent}
              addNewProd={addNewProd}
              editProduct={editProduct}
            />
          );
        })}
      </div>
      <button
        className="w-full justify-center bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded inline-flex items-center"
        onClick={addNewCateg}
      >
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width={20}
          height={20}
          viewBox="0 0 24 24"
          fill="none"
          stroke={"black"}
          strokeWidth="2"
          strokeLinecap="round"
          strokeLinejoin="round"
        >
          <line x1="12" y1="5" x2="12" y2="19"></line>
          <line x1="5" y1="12" x2="19" y2="12"></line>
        </svg>
        <span>Add category of products(ex. Drinks)</span>
      </button>
    </>
  );
}
