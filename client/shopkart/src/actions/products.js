import {
    GET_PRODUCTS,
    CREATE_PRODUCTS,
    UPDATE_PRODUCTS,
    DELETE_PRODUCTS,
    GET_CARTS,
    CART_PRODUCTS,
  } from "./types";
  
  import axios from "axios";
  
  // get the products in the database 
  export const getProducts = () => dispatch => {
    //console.log(localStorage.getItem("token"));
    return axios
      .get("http://localhost:8080/item")
      .then(res => {
        dispatch({
          type: GET_PRODUCTS,
          payload: res.data
        });
        //console.log(res.data);
      })
      .catch(err => {
        console.log(err);
      });
  };
  
  //create the products and send them to the database 
  export const createProducts = products => dispatch => {
    axios
      .post("http://localhost:8080/admin/item",{
        "itemName":products.itemName,
        "quantity":products.quantity,
        "price":products.price,
        "token":localStorage.getItem("token")
      }
      
      )
      .then(res => {
        dispatch({
          type: CREATE_PRODUCTS
        });
        alert("Added one product successfully");
        // dispatch(getProducts());
      })
      .catch(err => {
        console.log(err);
        alert("Try Again");
      });
  };
  
  //update the products and send them to the database 
  export const updateProducts = products => dispatch => {
    axios
      .put(
        "http://localhost:8080/admin/item/" ,
        {
        "itemName":products.itemName,
        "quantity":products.quantity,
        "price":products.price,
        "token":localStorage.getItem("token")
        }
      )
      .then(res => {
        dispatch({
          type: UPDATE_PRODUCTS
        });
        dispatch(getProducts());
        alert("Updated Successfully");
      })
      .catch(err => {
        console.log(err);
      });
  };
  
  //delete the product and send them to the database 
  export const deleteProducts = itemName => dispatch => {
    axios
      .delete("http://localhost:8080/admin/item",
       {
         data:{"itemName":itemName,
          "token": localStorage.getItem("token")}
      })
      .then(res => {
        dispatch({
          type: DELETE_PRODUCTS
        });
        dispatch(getProducts());
        alert("Deleted Successfully");
      })
      .catch(err => {
        console.log(err);
      });
  };

  // add the items to the cart 
  export const AddtoCart = PRODUCTS => dispatch => {
    axios
      .post("http://localhost:8080/cart", PRODUCTS,
       {
        headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
      }
      )
      .then(res => {
        dispatch({
          type: CART_PRODUCTS
        });
        alert("Product Added to Cart");
        // dispatch(getProducts());
      })
      .catch(err => {
        console.log(err);
        alert("Try Again");
      });
  };
  
  //get the values from cart 
  export const getCarts = () => dispatch => {
    console.log(localStorage.getItem("token"));
    return axios
      .post("http://localhost:8080/cart/state",
      {
        "token": localStorage.getItem("token")
      }
      )
      .then(res => {
        dispatch({
          type: GET_CARTS,
          payload: res.data
        });
        console.log(res.data);
      })
      .catch(err => {
        console.log(err);
      });
  };


