import React, { Component } from "react";
import { Link } from "react-router-dom";
// import DisplayProducts from "./DisplayProducts";
import { getProducts,deleteProducts } from "../actions/products";
import { connect } from "react-redux";
// import logo from "../logo.PNG";
import AdminNavbar from "./AdminNavbar";
import axios from "axios";

export class Navigation extends Component {
  componentWillMount() {
    this.props.getProducts();
  }

  componentDidMount(){
    if (!localStorage.getItem("token")) {
      this.props.history.push("/navigation");
    }
  }
  

  render() {
    //console.log(this.props.getProducts())
    return (
      <div>
        <div>
          <AdminNavbar />
        </div>
        <h2 style={{ textAlign: "center", marginTop: 2 + "em" }}>Products</h2>
        <div id="outerDiv">
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
                  {/*<button*/}
                  {/*  id="delBtn"*/}
                  {/*  onClick={() =>  axios.delete("http://localhost:8080/admin/item", {*/}
                  {/*      data:{*/}
                  {/*                    itemName: product.itemName,*/}
                  {/*                    token: localStorage.getItem("token")*/}
                  {/*          }}*/}

                  {/*                //returned msg is logged for dev confirmation purpose*/}
                  {/*            ).then(r => console.log(r.data))}*/}
                  {/*>*/}
                  {/*  Delete*/}
                  {/*</button>*/}
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

const mapStateToProps = state => ({
  products: state.productReducer.products
});

export default connect(
  mapStateToProps,
  { getProducts,deleteProducts }
)(Navigation);
