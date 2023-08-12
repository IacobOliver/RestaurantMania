import React from "react";
import { useState } from "react";
import { useRef } from "react";
import {
  Accordion,
  AccordionHeader,
  AccordionBody,
} from "@material-tailwind/react";
import ProductCard from "./ProductCard";

function Icon({ id, open }) {
  return (
    <svg
      xmlns="http://www.w3.org/2000/svg"
      fill="none"
      viewBox="0 0 24 24"
      strokeWidth={2}
      stroke="currentColor"
      className={`${
        id === open ? "rotate-180" : ""
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
          <p
            contentEditable={editName}
            className={`${editName ? "border border-white rounded-lg" : ""} mb-0`}
            ref={categRef}
          >
            {categ.name ? categ.name : "Add a category name"}
          </p>
          {editName ? (
            <i
              onClick={() => {
                editContentEvent(categRef, "name", categ.id);
                setEditName(false);
              }}
              className="fa fa-check ml-3 mr-3 hover:text-gray-400"
            >
              {" "}
            </i>
          ) : (
            <i
              onClick={() => {
                setEditName(true);
              }}
              className="fas fa-edit ml-3 mr-3 hover:text-gray-400"
            ></i>
          )}
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
        <button
          className="w-full justify-center bg-gray-300 hover:bg-gray-400 text-gray-800 font-bold py-2 px-4 rounded inline-flex items-center"
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
            stroke={"black"}
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
          >
            <line x1="12" y1="5" x2="12" y2="19"></line>
            <line x1="5" y1="12" x2="19" y2="12"></line>
          </svg>
          <span>Add a product(ex. Smoothie)</span>
        </button>
      </AccordionBody>
    </Accordion>
  );
}
