import React, { Component } from "react";
import { Link } from "react-router-dom";
// import DisplayProducts from "./DisplayProducts";
import { getProducts, AddtoCart } from "../actions/products";
import { connect } from "react-redux";
// import logo from "../logo.PNG";
import LoginNavbar from "./LoginNavbar";
import axios from "axios";
import AdsHolder from "./AdsHolder";



export class NavigationAfterLogin extends Component {
  componentDidMount() {
    this.props.getProducts();
  }



  componentWillMount() {
    if (!localStorage.getItem("token")) {
      this.props.history.push("/");
    }
  }


  onChange = e => {
    this.setState({ [e.target.name]: e.target.value });
  };


  render() {
    //console.log("NavigationAfterLogin "+this.props.getProducts())
  //   const [show, setShow] = useState(false);
  //   const handleClose = () => setShow(false);

  //   useEffect(() => {
  //     handleClose()
  // }, [product])

    return (
      <div>
        <div>
          <LoginNavbar />
        </div>
        <h2 style={{ textAlign: "center", marginTop: 2 + "em" }}>Products</h2>
        <div id="outerDiv">
          <marqee>  <AdsHolder/></marqee>
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
                      alert("Saved 1 " + product.itemName + " to cart")


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

const mapStateToProps = state => ({
  products: state.productReducer.products
});

export default connect(
  mapStateToProps,
  { getProducts, AddtoCart }
)(NavigationAfterLogin);
