package com.onefool.common.controller;

/***
 * 描述
 * @author Onefool
 * @version 1.0
 */
public interface ICoreController<T> extends
        ISelectController<T>,
        IInsertController<T>,
        IPagingController<T>,
        IDeleteController<T>,
        IUpdateController<T> {
}
