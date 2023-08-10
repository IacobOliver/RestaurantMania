import React from "react";
import { useState } from "react";
import { useRef } from "react";

export default function ProductCard({ product, prodId, categId, editProduct }) {
  const imgRef = useRef(null);
  const nameRef = useRef(null);
  const descriptionRef = useRef(null);
  const priceRef = useRef(null);
  const [editName, setEditName] = useState(false);
  const [editDescription, setEditDescription] = useState(false);
  const [editPrice, setEditPrice] = useState(false);

  function handleImageChange(event) {
    console.log("triggered");
    const file = event.target.files[0];
    if (!file) return;

    const image = imgRef.current;
    const reader = new FileReader();

    reader.onload = function () {
      const imageDataUrl = reader.result;
      image.src = imageDataUrl;
    };

    reader.readAsDataURL(file);
  }

  const EditButton = ({ edit, setEdit, field, elRef }) => {
    return (
      <>
        {edit ? (
          <i
            onClick={() => {
              editProduct(elRef, field, categId, prodId);
              setEdit(false);
            }}
            className="fa fa-check ml-3 mr-3 hover:text-gray-400"
          >
            {" "}
          </i>
        ) : (
          <i
            onClick={() => {
              setEdit(true);
            }}
            className="fas fa-edit ml-3 mr-3 hover:text-gray-400"
          ></i>
        )}
      </>
    );
  };

  return (
    <div className="h-24 md:h-36 mb-2 w-full flex rounded-lg bg-gray-600 flex-row">
      <label htmlFor={"fileInput" + prodId}>
        <img
          ref={imgRef}
          id="editableImage"
          className="h-full w-32 min-w-28 md:h-36 md:w-40 rounded object-cover"
          src={
            product.image?.imageUrl
              ? product.image.imageUrl
              : "https://icon-library.com/images/add-image-icon-png/add-image-icon-png-15.jpg"
          }
        />
      </label>
      <input
        disabled={false}
        type="file"
        id={"fileInput" + prodId}
        className="hidden"
        accept="image/*"
        onChange={(e) => handleImageChange(e, imgRef)}
      />
      <div className="flex w-full h-full flex-col justify-center m-1 md:m-3">
        <h5
          contentEditable={editName}
          ref={nameRef}
          className={
            `${editName ? "border border-white rounded-lg" : ""}` +
            "mb-0 text-base md:text-xl font-medium text-neutral-800 "
          }
        >
          {product.name ? product.name : "Product Name"}
        </h5>
        <EditButton
          edit={editName}
          setEdit={setEditName}
          field={"name"}
          elRef={nameRef}
        />
        <p
          contentEditable={editDescription}
          ref={descriptionRef}
          className={
            `${editDescription ? "border border-white rounded-lg" : ""}` +
            " mb-0 text-sm md:text-base text-neutral-100 overflow-hidden"
          }
        >
          {product.productDescription
            ? product.productDescription
            : "Product Description"}
        </p>
        <EditButton
          edit={editDescription}
          setEdit={setEditDescription}
          field={"productDescription"}
          elRef={descriptionRef}
        />
        <p
          contentEditable={editPrice}
          ref={priceRef}
          className={
            `${editPrice ? "border border-white rounded-lg" : ""}` +
            " flex justify-end self-end text-xs text-neutral-200 "
          }
        >
          {product.price ? product.price : 0} Ron
        </p>
        <EditButton
          edit={editPrice}
          setEdit={setEditPrice}
          field={"price"}
          elRef={priceRef}
        />
      </div>
      <div></div>
    </div>
  );
}
