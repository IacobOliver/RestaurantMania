import React from "react";
import { useState } from "react";
import { useRef } from "react";
import { checking } from "../Utils";

export default function ProductCard({ product, prodId, categId, editProduct }) {
  const imgRef = useRef(null);
  const nameRef = useRef(null);
  const descriptionRef = useRef(null);
  const priceRef = useRef(null);
  const [editName, setEditName] = useState(false);
  const [editDescription, setEditDescription] = useState(false);
  const [editPrice, setEditPrice] = useState(false);

  function handleImageChange(event) {
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
        {edit && checking.checkIfHolder()? (
          <i
            onClick={() => {
              editProduct(elRef, field, categId, prodId);
              setEdit(false);
            }}
            className="fa fa-check ml-3 mr-3 text-gray-200 hover:text-gray-400"
          >

          </i>
        ) : null}

        {!edit && checking.checkIfHolder() ?
          <i
            onClick={() => {
              setEdit(true);
            }}
            className="fas fa-edit ml-3 mr-3 text-gray-200 hover:text-gray-400"
          ></i> : null}
      </>
    );
  };

  const deleteProductEvent = (e) => {
    e.target.parentElement.parentElement.parentElement.parentElement.remove()
    console.log(prodId)
    fetch(`http://localhost:8080/product/delete/${prodId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },

    }).then(res => res.json())
      .catch(err => console.log("Error del product " + err))
  }

  return (
    <div className="h-40 mb-2 mx-1 w-full flex rounded-lg bg-gray-800 flex-row">
      <div>
        <label htmlFor={"fileInput" + prodId}>
          <img
            draggable={false}
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

        {checking.checkIfHolder() ?
        <input
          disabled={false}
          type="file"
          id={"fileInput" + prodId}
          className="hidden"
          accept="image/*"
          onChange={(e) => handleImageChange(e, imgRef)}
        /> : null}
      </div>


      <div className="flex w-full h-full flex-col justify-between p-2 ">

        <div>
          <div className="flex items-center justify-between mb-2">
            <div className=" flex items-center">
              <h5
                contentEditable={editName}
                ref={nameRef}
                className={
                  `${editName ? "border border-white rounded-lg" : ""}` +
                  "mb-0 text-base md:text-xl font-bold text-gray-200 "
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
            </div>
            
            {checking.checkIfHolder() ?   <i onClick={deleteProductEvent} className="fas fa-trash mr-3 text-md text-gray-200 bg-gray-800 rounded-lg p-2"></i> : null}
          
          </div>

          <div className="flex items-center">
            <p
              contentEditable={editDescription}
              ref={descriptionRef}
              className={
                `${editDescription ? "border border-white rounded-lg" : ""}` +
                " mb-0 text-sm md:text-base text-neutral-100 bg-gray-800 p-2 rounded-xl overflow-y-scroll custom-scrollbar max-h-14 max-w-2xl"
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
          </div>
        </div>

        <div className=" flex items-center justify-end mr-5 ">
          <div className = " flex items-center ">
          <p
            contentEditable={editPrice}
            ref={priceRef}
            className={
              `${editPrice ? "border border-white rounded-lg" : ""}` +
              " flex justify-end self-end text-xl text-neutral-200 mb-0"
            }
          >
            {product.price ? product.price : 0}
          </p>
          <p className=" mb-0 text-xl text-neutral-200">‎ Ron</p>
          </div>

          <EditButton
            edit={editPrice}
            setEdit={setEditPrice}
            field={"price"}
            elRef={priceRef}
          />
        </div>

      </div>

    </div>
  );
}
