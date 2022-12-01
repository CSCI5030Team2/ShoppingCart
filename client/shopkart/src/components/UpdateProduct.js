import React, { Component } from "react";
import { connect } from "react-redux";
import { getProducts, updateProducts } from "../actions/products";
import { Link } from "react-router-dom";
import AdminNavbar from "./AdminNavbar";

export class UpdateProducts extends Component {
  state = {
    id: this.props.match.params.productid,
    item_name: this.props.location.state.product.item_name,
    price: this.props.location.state.product.price,
    quantity: this.props.location.state.product.quantity
  };

  componentWillMount() {
    if (!localStorage.getItem("token")) {
      this.props.history.push("/");
    }
  }

  onChange = e => {
    this.setState({ [e.target.name]: e.target.value });
  };

  render() {
    console.log(this.props);
    return (
      <div>
        <AdminNavbar />
        <div
          className="container"
          style={{ height: 30 + "em", marginTop: 1 + "em" }}
          align="center"
        >
          {/* <form> */}
          <h1>Update Product</h1>
          <input
            type="text"
            name="name"
            placeholder=" Product Name"
            onChange={this.onChange}
            value={this.state.item_name}
          />
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
              name="quantity"
              placeholder=" Product Quantity"
              onChange={this.onChange}
              value={this.state.quantity}
            />
          </p>
          <p>
            <Link to="/displayproducts">
              <button
                onClick={() =>
                  this.props.updateProducts({
                    name: this.state.name,
                    price: this.state.price,
                    quantity: this.state.quantity,
                    id: this.state.id
                  })
                }
                onChange={this.onChange}
              >
                Update
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
  product: state.productReducer.product
});

export default connect(
  mapStateToProps,
  { getProducts, updateProducts }
)(UpdateProducts);