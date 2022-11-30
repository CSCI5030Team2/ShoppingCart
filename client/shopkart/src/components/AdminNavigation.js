import React, { Component } from "react";
import { Link } from "react-router-dom";
// import DisplayProducts from "./DisplayProducts";
import { getProducts } from "../actions/products";
import { connect } from "react-redux";
// import logo from "../logo.PNG";
import Navbar from "./Navbar";
import AdsHolder from "./AdsHolder";

// import EditForm from './EditForm'
// import { Modal, Button, OverlayTrigger, Tooltip } from 'react-bootstrap';
// import { useState, useEffect } from "react"

export class AdminNavigation extends Component {
  componentWillMount() {
    this.props.getProducts();
  }

  render() {
    //console.log(this.props.getProducts())
    return (
      <div>
        <div>
          <Navbar />
        </div>
        <h2 style={{ textAlign: "center", marginTop: 2 + "em" }}>Products</h2>
        <div id="outerDiv">
          <AdsHolder />
          {this.props.products.map(product => (
            <div>
              <div
                className="AdminProductsdisplay"
                style={{ width: 15 + "em" }}
              >
                <p>
                  <b>Product Name : </b> {product.itemName}
                </p>
                <p>
                  <b> Quantity : </b> {product.quantity}
                </p>
                <p>
                  <b>Price : </b> ${product.price}
                </p>

                {/* insert edit button here 
                insert delete button here  */}

                {<div>
                  <button
                    onClick={() =>

                      //this is ugly, try to beautify it a bit

                      alert("updated 1 " + product.itemName + " to cart")


                    }
                    id="editBtn"
                  >
                    Edit Product
                  </button>
                </div>}

                {/* <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                      <Modal.Title>
                        Edit Item
                      </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                      <EditForm theProduct={product} />
                    </Modal.Body>
                    <Modal.Footer>
                      <Button variant="secondary" onClick={handleClose}>
                        Close Button
                      </Button>
                    </Modal.Footer>
                  </Modal> */}

              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  products: state.productReducer.products
});

export default connect(
  mapStateToProps,
  { getProducts }
)(AdminNavigation);
