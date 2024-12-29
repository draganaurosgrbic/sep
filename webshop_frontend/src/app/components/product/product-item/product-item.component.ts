import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Product } from 'src/app/models/product';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';
import { DIALOG_CONFIG, SNACKBAR_CLOSE_BUTTON, SNACKBAR_ERROR_CONFIG, SNACKBAR_ERROR_TEXT, SNACKBAR_SUCCESS_CONFIG, SNACKBAR_SUCCESS_TEXT } from 'src/app/utils/popup';
import { Route } from 'src/app/utils/route';
import { environment } from 'src/environments/environment';
import { DeleteConfirmationComponent } from '../../utils/delete-confirmation/delete-confirmation.component';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.scss']
})
export class ProductItemComponent {

  constructor(
    private productService: ProductService,
    private cartService: CartService,
    private orderService: OrderService,
    private dialog: MatDialog,
    private snackbar: MatSnackBar
  ) { }

  @Input() product: Product;
  @Input() actions: 'crud' | 'add_to_cart' | 'remove_from_cart';
  @Output() refreshProducts = new EventEmitter<void>();

  cartPendingId: number = null;
  orderPendingId: number = null;

  edit(product: Product) {
    return `/${Route.PRODUCT_FORM}/${product.id}`
  }

  async delete(product: Product) {
    const options: MatDialogConfig = { ...DIALOG_CONFIG, ...{ data: () => this.productService.delete(product.id) } };
    const res = await this.dialog.open(DeleteConfirmationComponent, options).afterClosed().toPromise()
    if (res) {
      this.refreshProducts.emit();
    }
  }

  async addToCart(product: Product) {
    this.cartPendingId = product.id;
    try {
      await this.cartService.addToCart(product.id).toPromise();
      this.cartPendingId = null;
      this.snackbar.open(SNACKBAR_SUCCESS_TEXT, SNACKBAR_CLOSE_BUTTON, SNACKBAR_SUCCESS_CONFIG);
    } catch {
      this.cartPendingId = null;
      this.snackbar.open(SNACKBAR_ERROR_TEXT, SNACKBAR_CLOSE_BUTTON, SNACKBAR_ERROR_CONFIG);
    }
  }

  async remove_from_cart(product: Product) {
    this.cartPendingId = product.id;
    try {
      await this.cartService.removeFromCart(product.id).toPromise();
      this.cartPendingId = null;
      this.snackbar.open(SNACKBAR_SUCCESS_TEXT, SNACKBAR_CLOSE_BUTTON, SNACKBAR_SUCCESS_CONFIG);
      this.refreshProducts.emit();
    } catch {
      this.cartPendingId = null;
      this.snackbar.open(SNACKBAR_ERROR_TEXT, SNACKBAR_CLOSE_BUTTON, SNACKBAR_ERROR_CONFIG);
    }
  }

  async order(product: Product) {
    this.orderPendingId = product.id;
    try {
      const order = await this.orderService.order(product.id).toPromise();
      this.orderPendingId = null;
      this.snackbar.open(SNACKBAR_SUCCESS_TEXT, SNACKBAR_CLOSE_BUTTON, SNACKBAR_SUCCESS_CONFIG);
      this.refreshProducts.emit();
      window.open(`${environment.selectPaymentMethodUrl}/${order.pspId}`);
    }
    catch {
      this.orderPendingId = null;
      this.snackbar.open(SNACKBAR_ERROR_TEXT, SNACKBAR_CLOSE_BUTTON, SNACKBAR_ERROR_CONFIG);
    }
  }

}
