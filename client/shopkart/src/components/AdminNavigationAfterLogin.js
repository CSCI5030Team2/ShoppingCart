import React, { Component } from "react";
import { Link } from "react-router-dom";
// import DisplayProducts from "./DisplayProducts";
import { getProducts } from "../actions/products";
import { connect } from "react-redux";
// import logo from "../logo.PNG";
import Navbar from "./Navbar";
import AdsHolder from "./AdsHolder";

export class Navigation extends Component {
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
                <button
                    onClick={() => {
                      this.props.history.push(
                        "/UpdateProduct/" + product.id,
                        {
                          product
                        }
                      );
                    }}
                    id="editBtn"
                  >
                    Edit
                  </button>
                  <button
                    id="delBtn"
                    onClick={() => this.props.deleteProducts(product.id)}
                  >
                    Delete
                  </button>
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
)(Navigation);
