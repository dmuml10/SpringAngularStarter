// angular > core
import {NgModule} from "@angular/core";
// angular > forms
import {FormsModule} from "@angular/forms";
// angular > http
import {HttpClientModule} from "@angular/common/http";
// angular > platform browser
import {BrowserModule} from "@angular/platform-browser";
// angular > platform browser > animations
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
// component
import {AppComponent} from "./app.component";
import {ForgotPasswordComponent} from "./component/login/forgot-password.component"
import {LoginComponent} from "./component/login/login.component";
import {DummiesComponent} from "./component/dummy/dummy.component";
// service
import {AuthenticationService} from "./service/authentication.service";
import {DummyService} from "./service/dummy.service";
// routing
import {AppRoutingModule} from "./app-routing.model";
// util
import {CanActivateAuthGuard} from "./util/can-activate.authguard";
import {INJECTABLE_CONSTANTS} from "./util/injectable-constants";

@NgModule({
	declarations: [
		// component
		AppComponent,
		ForgotPasswordComponent,
		LoginComponent,
		DummiesComponent
	],
	imports: [
		// angular > forms
		FormsModule,
		// angular > http
		HttpClientModule,
		// angular > platform browser
		BrowserModule,
		// angular > platform browser > animations
		BrowserAnimationsModule,
		// routing
		AppRoutingModule,
	],
	providers: [
		// service
		AuthenticationService,
		DummyService,
		// util
		CanActivateAuthGuard,
		INJECTABLE_CONSTANTS,
	],
	bootstrap: [AppComponent]
})
export class AppModule {}