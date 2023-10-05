import React from "react";
import { useState, useEffect } from "react";
import CategoryAccordeon from "./CategoryAccordeon";
import Loading from "../Loading";

const ITEMS_PER_PAGE = 1;

export default function RestaurantMenu({
  isHolder,
  thisRestaurant,
  setThisRestaurant,
}) {
  const [open, setOpen] = useState(1);
  const [loading, setLoading] = useState(false);
  let thisURL =window.document.URL;

  useEffect(() => {
    let page1 = 0;
    let loadedCategs = [];
    async function fetchData() {
      if (thisRestaurant) {
        fetch(
          `http://localhost:8080/categoryProduct/getSome/${thisRestaurant.menu.id}/${page1}/${ITEMS_PER_PAGE}`
        )
          .then((res) => res.json())
          .then((data) => {
            if (data.length === 0 || thisURL != window.document.URL) {
              console.log("finished")
              return;
            }
            loadedCategs = [...loadedCategs, ...data];
            setThisRestaurant({
              ...thisRestaurant,
              menu: {
                ...thisRestaurant.menu,
                categoryProducts: [...loadedCategs],
              },
            });
            setLoading(false);
            page1++;
            fetchData();
          });
      }
    }
    fetchData();
  }, []);

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
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
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
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
    })
      .then((res) => res.json())
      .then((newProd) => {
        console.log(newProd);
        let thisRestaurantCopy = { ...thisRestaurant };

        thisRestaurantCopy.menu.categoryProducts.map((categ) => {
          if (categ.id === categId) {
            if (categ.products) {
              categ.products.push(newProd);
            } else {
              categ.products = [newProd];
            }
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
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
      body: contentRef.current.textContent,
    }).catch((err) => {
      console.error("Err at updating categName " + err);
    });

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
    console.log("prod ID " + prodId);
    console.log("cat Id " + categId);

    fetch(`http://localhost:8080/product/update/${key}/${prodId}`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("token")}`,
      },
      body: contentRef.current.textContent,
    })
      .then((res) => res.json())
      .catch((err) => {
        console.err("Err at updating categName " + err);
      });

    for (let categ of thisRestaurantCopy.menu.categoryProducts) {
      if (categ.id === categId) {
        for (let prod of categ.products) {
          if (prod.id === prodId) {
            prod[key] = contentRef.current.textContent;
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
      {isHolder ? (
        <div className="w-full flex justify-center bg-gradient-to-r from-gray-900 via-gray-800 to-gray-700 py-3 border-b-2 border-b-white">
          <button
            className=" w-1/2 justify-center font-bold py-2 px-4 rounded inline-flex items-center   text-gray-200 bg-gradient-to-r from-yellow-400 via-yellow-500 to-yellow-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-yellow-300 dark:focus:ring-yellow-800 shadow-lg shadow-yellow-500/50 dark:shadow-lg dark:shadow-yellow-800/80  text-sm  text-center"
            onClick={addNewCateg}
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width={20}
              height={20}
              viewBox="0 0 24 24"
              fill="none"
              stroke={"white"}
              strokeWidth="4"
              strokeLinecap="round"
              strokeLinejoin="round"
              className="mx-2"
            >
              <line x1="12" y1="5" x2="12" y2="19"></line>
              <line x1="5" y1="12" x2="19" y2="12"></line>
            </svg>

            <span>Add category of products(ex. Drinks)</span>
          </button>
        </div>
      ) : null}



      <div className="md:mr-2 rounded-t-lg divide-y-2 border-b-2 bg-gradient-to-r from-gray-900 via-gray-800 to-gray-700 lg:overflow-visible">
        {loading ? <Loading /> : null}
        {thisRestaurant?.menu.categoryProducts?.map((categ, index) => {
          return (
            <CategoryAccordeon
              isHolder={isHolder}
              key={index}
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



    </>
  );
}
