export const API_BASE_URL: string = 'api/';
export const AUTH_SERVICE_URL: string = 'http://localhost:8080/auth';
export const DUMMY_SERVICE_URL: string = API_BASE_URL + 'dummy';

export const INJECTABLE_CONSTANTS: any[] = [
	{provide: AUTH_SERVICE_URL, useValue: AUTH_SERVICE_URL},
	{provide: DUMMY_SERVICE_URL, useValue: DUMMY_SERVICE_URL}
];