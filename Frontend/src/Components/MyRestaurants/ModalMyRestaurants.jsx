import React from "react";
import MyRestaurantsPopUp from "./MyRestaurantsPopUp";
import Modal from "react-bootstrap/Modal";


export default function ModalMyRestaurants({show , onHide }) {
    return (
        <>
            <Modal
                show={show}
                onHide={onHide}
                aria-labelledby="contained-modal-title-vcenter"
                centered
                size="m"
            >

                <Modal.Body className="bg-tc1 rounded-xl">
                    <div
                        style={{
                            width: "100%",
                            height: "550px",
                        }}
                    >

                      <MyRestaurantsPopUp/>
                    </div>

                </Modal.Body>
            
            </Modal>
        </>
    )
}