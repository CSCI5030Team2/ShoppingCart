import React, { Component } from "react";
import { Link } from "react-router-dom";
import { getProducts,deleteProducts } from "../actions/products";
import { connect } from "react-redux";
import AdminNavbar from "./AdminNavbar";
import axios from "axios";

export class Navigation extends Component {
    // This function will call the values from the database using getProducts mentioned in Actions 
  componentWillMount() {
    this.props.getProducts();
  }

 // This function will check if the browser has token present or no if its not present then send the admin to the route mentioned below
  componentDidMount(){
    if (!localStorage.getItem("token")) {
      this.props.history.push("/navigation");
    }
  }
  
  render() {
    return (
      <div>
        <div>
          <AdminNavbar />
        </div>
        <h2 style={{ textAlign: "center", marginTop: 2 + "em" }}>Products</h2>
        <div id="outerDiv">
            {/* this function is used to map the values that are called using the above componentWillMount and displaying it on the front end to the user */}
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
                {/* this function will push the values to the below mentioned route along with the itemName as in the database itemName is unique  */}
                <button
                    onClick={() => {
                      this.props.history.push(
                        "/UpdateProduct/" + product.itemName,
                        {
                          product
                        }
                      );
                    }}
                    id="editBtn"
                  >
                    Edit
                  </button>
                  {/* this function will delete the item from the database based on the itemName */}
                  <button
                      id="delBtn"
                      onClick={() => this.props.deleteProducts(product.itemName)}
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

// These functions are used to send the above values to the reducers where the values are stored and sent the their respective actions 
const mapStateToProps = state => ({
  products: state.productReducer.products
});

// calling all the functions which are used on this page 
export default connect(
  mapStateToProps,
  { getProducts,deleteProducts }
)(Navigation);
