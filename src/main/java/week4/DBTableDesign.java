package week4;

/**
 * 1 - 1
 *      1. A(id), B(id, a_id)
 *      2. A(id, b_id), B(id)
 * 1 - m    A 1 - m B  :  A(id),  B(id, a_id)
 * m - m    A m - m B  :  A(id),  A_B(id, a_id, b_id), B(id)
 *
 *  ******************
 * Entity Attribute Value
 *
 * id, name, value type,  value, document_id
 *  1, "age",  "number",  5    ,   a101
 *  2, "fullname", "string", "..", a101
 *  3, "gender", "string", "..",   b102
 *
 *
 *  id, details
 *  a101, ...
 *  a102, ...
 *  ******************
 *  Normalization
 *  1st -> same cell cannot hold multiple values
 *         name
 *        F,M,L
 *  2nd -> non-prime attributes fully depend on prime attributes
 *
 *         (A, B) ->  C, D
 *         A -> C, B -> C
 *
 *         (book_id, author_id)(pk), book_name, author_name
 *             1        A              Java         Jessie
 *             1        B              Java         Alex
 *             2        A              C#           Jessie
 *
 *         book_id -> book_name
 *         author_id -> author_name
 *         author_id -> book_name (X)
 *
 *  3rd -> no transitive relationship among non-prime attributes
 *
 *         id, name, address_id, address_name
 *
 *
 *
 *
 */
