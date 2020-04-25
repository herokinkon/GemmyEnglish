export class AppConstant {
  public static readonly serverURL = 'http://localhost:8080/api';
  public static readonly HEADER_TOKEN = 'X-Token';
  public static readonly STORED_TOKEN = 'userToken';
}

export enum ENTITY_ACTION { CREATE, EDIT, DELETE }
