import React from "react";
import { useState } from "react";
import { useRef } from "react";
import {
  Accordion,
  AccordionHeader,
  AccordionBody,
} from "@material-tailwind/react";
import ProductCard from "./ProductCard";
import { checking } from "../Utils";

function Icon({ id, open }) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      fill="none"
      viewBox="0 0 24 24"
      strokeWidth={2}
      stroke="currentColor"
      className={`${id === open ? "rotate-180" : ""
        } h-5 w-5 transition-transform`}
    >
      <path
        strokeLinecap="round"
        strokeLinejoin="round"
        d="M19.5 8.25l-7.5 7.5-7.5-7.5"
      />
    </svg>
  );
}



export default function CategoryAccordeon({
  open,
  index,
  handleOpen,
  categ,
  editContentEvent,
  addNewProd,
  editProduct,
}) {
  const categRef = useRef(null);
  const [editName, setEditName] = useState(false);

  const deleteCategoryProduct = (e) => {
    e.target.parentElement.parentElement.parentElement.remove()
    console.log(categ.id)
    fetch(`http://localhost:8080/categoryProduct/delete/${categ.id}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        "Authorization" : `Bearer ${localStorage.getItem("token")}`
      },

    }).then(res => res.json())
      .catch(err => console.log("Error del categPrduct " + err))

  }



  return (
    <Accordion
      open={open === index + 1}
      icon={<Icon id={index + 1} open={open} key={index + 1} />}
    >
      <AccordionHeader
        className="p-4"
        onClick={(e) => handleOpen(index + 1, e)}
      >
        <div className="flex flex-row items-center">
          {checking.checkIfHolder() ? <i onClick={deleteCategoryProduct} className="fas fa-trash mr-3 text-md text-gray-200 bg-gray-800 rounded-lg p-2"></i> : null}


          <p
            contentEditable={editName}
            className={`${editName ? "border border-white rounded-lg" : ""} mb-0`}
            ref={categRef}
          >
            {categ.name ? categ.name : "Add a category name"}
          </p>


          {editName && checking.checkIfHolder() ? (
            <i
              onClick={() => {
                editContentEvent(categRef, "name", categ.id);
                setEditName(false);
              }}
              className="fa fa-check ml-3 mr-3 hover:text-gray-400"
            >
              {" "}
            </i>
          ) : null }

            {!editName && checking.checkIfHolder() ?
          <i
            onClick={() => {
              setEditName(true);
            }}
            className="fas fa-edit ml-3 mr-3 hover:text-gray-400"
          ></i> : null }

        </div>
      </AccordionHeader>
      <AccordionBody>
        {categ.products?.map((prod, index) => (
          <ProductCard
            product={prod}
            key={index}
            prodId={prod.id}
            editProduct={editProduct}
            categId={categ.id}
          />
        ))}


        {checking.checkIfHolder() ?
          <div className="w-full flex justify-center">
            <button
              className="w-11/12 justify-center inline-flex items-center  text-white bg-gradient-to-r from-yellow-400 via-yellow-500 to-yellow-600 hover:bg-gradient-to-br focus:ring-4 focus:outline-none focus:ring-yellow-300 dark:focus:ring-yellow-800 shadow-lg shadow-yellow-500/50 dark:shadow-lg dark:shadow-yellow-800/80 font-medium rounded-lg text-sm px-5 py-2.5 text-center"
              onClick={() => {
                addNewProd(categ.id);
              }}
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
              <span>Add a product(ex. Smoothie)</span>
            </button>
          </div> : null}


      </AccordionBody>
    </Accordion>
  );
}
