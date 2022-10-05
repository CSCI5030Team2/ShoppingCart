import React, { Component } from "react";
import { getProducts, createProducts } from "../actions/products";
import { connect } from "react-redux";
import AdminNavbar from "./AdminNavbar";
import { Link } from "react-router-dom";

export class CreateProduct extends Component {
  constructor(props) {
    super(props);
    this.onCreate = this.onCreate.bind(this);
  }


  state = {
    name: "",
    price: "",
    category: "",
    description: "",
    quantity: ""
  };

  onChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  onCreate() {
    let product = {
      name: this.state.name,
      price: this.state.price,
      category: this.state.category,
      description: this.state.description,
      quantity: this.state.quantity
    };
    this.props.createProducts(product);
    this.setState({
      name: "",
      price: "",
      category: "",
      description: "",
      quantity: ""
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
              name="name"
              placeholder=" Product Name"
              onChange={this.onChange}
              value={this.state.name}
            />
          </p>
          <p>
            <input
              type="text"
              name="price"
              placeholder=" Product Price"
              onChange={this.onChange}
              value={this.state.price}
            />{" "}
          </p>
          <p>
            <input
              type="text"
              name="category"
              placeholder=" Product Category"
              onChange={this.onChange}
              value={this.state.category}
            />
          </p>
          <p>
            <input
              type="text"
              name="description"
              placeholder=" Product Description"
              onChange={this.onChange}
              value={this.state.description}
            />
          </p>
          <p>
            <input
              type="text"
              name="quantity"
              placeholder=" Product Quantity"
              onChange={this.onChange}
              value={this.state.quantity}
            />
          </p>
          <p>
            <Link to="/displayproducts">
              <button onChange={this.onChange} onClick={this.onCreate}>
                Create
              </button>
            </Link>
          </p>
          {/* </form> */}
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
  { getProducts, createProducts }
)(CreateProduct);
