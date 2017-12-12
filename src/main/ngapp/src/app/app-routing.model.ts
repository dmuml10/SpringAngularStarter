// angular > core
import {NgModule} from "@angular/core";
// angular > router
import {RouterModule, Routes} from "@angular/router";
// component
import {ForgotPasswordComponent} from "./component/login/forgot-password.component";
import {LoginComponent} from "./component/login/login.component";
import {DummiesComponent} from "./component/dummy/dummy.component";
//util
import {CanActivateAuthGuard} from "./util/can-activate.authguard";

const routes: Routes = [
	{path: '', redirectTo: 'dummies', pathMatch: 'full'},
	{path: 'forgotPassword', component: ForgotPasswordComponent},
	{path: 'login', component: LoginComponent},
	{path: 'dummies', component: DummiesComponent, canActivate: [CanActivateAuthGuard]},
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {}