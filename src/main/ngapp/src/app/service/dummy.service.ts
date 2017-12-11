// angular > core
import {Inject, Injectable} from "@angular/core";
// angular > http
import {HttpClient} from "@angular/common/http";
// model > enum
import {HttpRequestType} from "../model/enum/http-request-type.enum";
// service
import {AbstractService} from "./abstract-service";
// util
import {DUMMY_SERVICE_URL} from "../util/injectable-constants";
// rxjs
import {Observable} from "rxjs/Observable";
import {User} from "../model/entity/user";

@Injectable()
export class DummyService extends AbstractService {

	public constructor(http: HttpClient, @Inject(DUMMY_SERVICE_URL) baseUrl: string) {
		super(http, baseUrl);
	}

	public get(id: number): Observable<User> {
		return super.httpRequest(HttpRequestType.GET, `get/${id}`);
	}

}