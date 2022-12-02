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

  // This function will check if the browser has token present or no if its not present then send the admin to the route mentioned below
  componentWillMount() {
    if (!localStorage.getItem("token")) {
      this.props.history.push("/");
    }
  }
  
  //it stores the values which are mentioned in the text boxes below 
  state = {
    itemName: "",
    quantity: "",
    price: "",
  };

  //helps change the values of textboxes when someone indends to enter a text
  onChange = event => {
    this.setState({ [event.target.name]: event.target.value });
  };

  //this function sends the data which is stored in the variables to createProducts and then sets the state blank if that does not happen 
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
         {/* aligning the component to the center so it looks more appealing */}
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
           {/* this button will send the values to the onLogin Function when it is pressed and then link to the below route if successful */}
          <p>          
              <button onChange={this.onChange} onClick={this.onCreate}>
                Create
              </button>
          </p>
         
          {/* </form> */}
        </div>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  // In order to send the data to the database Reducer is used using the variable products
  products: state.productReducer.products
});

// calling all the functions which are used on this page 
export default connect(
  mapStateToProps,
  {createProducts }
)(CreateProduct);
