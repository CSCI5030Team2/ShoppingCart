import React, { Component } from "react";
import { getProducts,AddtoCart } from "../actions/products";
import { connect } from "react-redux";
import LoginNavbar from "./LoginNavbar";
import axios from "axios";
import AdsHolder from "./AdsHolder";

export class NavigationAfterLogin extends Component {
   // This function will check if the browser has token present or no if its not present then send the user to the route mentioned below
  componentDidMount() {
    this.props.getProducts();
//     if (!localStorage.getItem("token")) {
//       this.props.history.push("/");
//     }
  }



  
  onChange = e => {
    this.setState({ [e.target.name]: e.target.value });
  };

    
  render() {
    return (
      <div>
        <div>
          <LoginNavbar />
        </div>
        <h2 style={{ textAlign: "center", marginTop: 2 + "em" }}>Products</h2>
        <div id="outerDiv">
           <AdsHolder/>
            {/* this function is used to map the values that are called using the above componentWillMount and displaying it on the front end to the user */}
            {
                this.props.products.map(product => (
                    <div>
                      <div
                        className="UserProductDisplay"
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
                              //console.log(localStorage.getItem("token"))
                              //console.log(product.itemName)

                              //temp solution, this does not use product.js AddtoCart implementation
                              axios.post("http://localhost:8080/cart",
                                  {
                                      token: localStorage.getItem("token"),
                                      itemName: product.itemName,
                                      quantity: 1
                                  }

                                  //returned msg is logged for dev confirmation purpose
                              ).then(r => console.log(r.data))

                              //this is ugly, try to beautify it a bit
                              alert("Saved 1 " + product.itemName +" to cart")


                          }}
                          id="editBtn"
                        >
                          Add To Cart
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
  { getProducts,AddtoCart }
)(NavigationAfterLogin);
