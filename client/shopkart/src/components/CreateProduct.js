import React, { Component } from "react";
import {createProducts } from "../actions/products";
import { connect } from "react-redux";
import AdminNavbar from "./AdminNavbar";
import { Link } from "react-router-dom";

export class CreateProduct extends Component {
  constructor(props) {
    super(props);
    this.onCreate = this.onCreate.bind(this);
  }


  state = {
    itemName: "",
    quantity: "",
    price: "",
  };

  onChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  onCreate() {
    let product = {
      itemName: this.state.itemName,
      quantity: this.state.quantity,
      price: this.state.price,

    };
    this.props.createProducts(product);
    this.setState({
      itemName: "",
      quantity: "",
      price: "",
    });
  }

  render() {
    return (
      <div>
        <AdminNavbar />
        <div
          className="container"
          style={{ marginTop: 1 + "em", height: "auto" }}
          align="center"
        >
          {/* <form> */}
          <h1>Add Product</h1>
          <p>
            <input
              type="text"
              name="itemName"
              placeholder=" Product Name"
              onChange={this.onChange}
              value={this.state.itemName}
            />
          </p>
          <p>
            <input
              type="text"
              name="quantity"
              placeholder="Enter Quantity"
              onChange={this.onChange}
              value={this.state.quantity}
            />{" "}
          </p>
          <p>
            <input
              type="text"
              name="price"
              placeholder=" Enter Price"
              onChange={this.onChange}
              value={this.state.price}
            />
          </p>
          <p>          
              <button onChange={this.onChange} onClick={this.onCreate}>
                Create
              </button>
          </p>
         
          {/* </form> */}
        </div>
        <button>Add Product</button>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  products: state.productReducer.products
});

export default connect(
  mapStateToProps,
  {createProducts }
)(CreateProduct);
